
package com.college.leavemanagement.service;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.college.leavemanagement.entity.AppUser;
import com.college.leavemanagement.repository.AppUserRepository;

import jakarta.ws.rs.core.Response;

@Service
public class KeycloakUserService {

    private final Keycloak keycloak;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${keycloak.admin.realm}")
    private String realm;

    public KeycloakUserService(
            Keycloak keycloak,
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder) {

        this.keycloak = keycloak;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // CREATE OR REUSE ADMIN USER
    public String createAdmin(
            String firstName,
            String lastName,
            String email,
            String password) {

        RealmResource realmResource = keycloak.realm(realm);

        List<UserRepresentation> existingUsers =
                realmResource.users().searchByEmail(email, true);

        String userId;

        if (!existingUsers.isEmpty()) {

            userId = existingUsers.get(0).getId();

        } else {

            UserRepresentation user = new UserRepresentation();

            user.setEnabled(true);
            user.setEmail(email);
            user.setUsername("admin1");
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmailVerified(false);

            user.setCredentials(
                    List.of(createPasswordCredential(password)));

            Response response = realmResource.users().create(user);

            try {
                if (response.getStatus()
                        != Response.Status.CREATED.getStatusCode()) {
                    throw new RuntimeException(
                            "Failed to create Admin. Keycloak status: "
                                    + response.getStatus());
                }

                userId = response.getLocation()
                        .getPath()
                        .replaceAll(".*/", "");

            } finally {
                response.close();
            }
        }

        assignRealmRole(realmResource, userId, "ADMIN");

        return userId;
    }

    // CREATE STUDENT IN KEYCLOAK AND POSTGRESQL
    public String createStudent(
            String firstName,
            String lastName,
            String email,
            String password) {

        // Check PostgreSQL for duplicate email
        if (appUserRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "A user with this email already exists in the database");
        }

        RealmResource realmResource = keycloak.realm(realm);

        // Check Keycloak for duplicate email
        checkExistingEmail(realmResource, email);

        UserRepresentation user = new UserRepresentation();

        user.setEnabled(true);
        user.setEmail(email);
        user.setUsername(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailVerified(false);

        user.setCredentials(
                List.of(createPasswordCredential(password)));

        Response response = realmResource.users().create(user);

        String userId = null;

        try {

            if (response.getStatus()
                    != Response.Status.CREATED.getStatusCode()) {
                throw new RuntimeException(
                        "Failed to create Student. Keycloak status: "
                                + response.getStatus());
            }

            userId = response.getLocation()
                    .getPath()
                    .replaceAll(".*/", "");

            // Assign STUDENT role in Keycloak
            assignRealmRole(realmResource, userId, "STUDENT");

            // Create student record in PostgreSQL
            AppUser appUser = new AppUser();

            appUser.setEmail(email);
            appUser.setPassword(passwordEncoder.encode(password));
            appUser.setRole("STUDENT");
            appUser.setKeycloakId(userId);

            appUserRepository.save(appUser);

            return userId;

        } catch (RuntimeException e) {

            // Remove Keycloak user if role assignment or DB save fails
            if (userId != null) {
                try {
                    realmResource.users().get(userId).remove();
                } catch (Exception cleanupException) {
                    e.addSuppressed(cleanupException);
                }
            }

            throw e;

        } finally {
            response.close();
        }
    }

    // CHECK EXISTING EMAIL IN KEYCLOAK
    private void checkExistingEmail(
            RealmResource realmResource,
            String email) {

        List<UserRepresentation> existingUsers =
                realmResource.users().searchByEmail(email, true);

        if (!existingUsers.isEmpty()) {
            throw new IllegalArgumentException(
                    "A user with this email already exists in Keycloak");
        }
    }

    // CREATE PASSWORD CREDENTIAL
    private CredentialRepresentation createPasswordCredential(
            String password) {

        CredentialRepresentation credential =
                new CredentialRepresentation();

        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(true);

        return credential;
    }

    // ASSIGN REALM ROLE
    private void assignRealmRole(
            RealmResource realmResource,
            String userId,
            String roleName) {

        RoleRepresentation role =
                realmResource.roles()
                        .get(roleName)
                        .toRepresentation();

        realmResource.users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(List.of(role));
    }
}
