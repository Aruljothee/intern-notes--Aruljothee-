
package com.college.leavemanagement.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.college.leavemanagement.entity.AppUser;
import com.college.leavemanagement.repository.AppUserRepository;

@Service
public class StudentProvisioningService {

    private final AppUserRepository appUserRepository;

    public StudentProvisioningService(
            AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    public AppUser provisionStudent(Jwt jwt) {

        String keycloakId = jwt.getSubject();
        String email = jwt.getClaimAsString("email");

        if (keycloakId == null || keycloakId.isBlank()
                || email == null || email.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Keycloak user ID and email are required");
        }

        // Verify STUDENT realm role
        Map<String, Object> realmAccess =
                jwt.getClaim("realm_access");

        Object rolesObject = realmAccess != null
                ? realmAccess.get("roles") : null;

        boolean isStudent = rolesObject instanceof Collection<?> roles
                && roles.contains("STUDENT");

        if (!isStudent) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Student role is required");
        }

        final String normalizedEmail =
                email.trim().toLowerCase();

        AppUser student = appUserRepository
                .findByKeycloakId(keycloakId)
                .orElseGet(() -> appUserRepository
                        .findByEmail(normalizedEmail)
                        .orElse(null));

        if (student == null) {
            student = new AppUser();
            student.setEmail(normalizedEmail);
            student.setRole("STUDENT");
            student.setPassword(null);
        } else if (!"STUDENT".equalsIgnoreCase(student.getRole())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "This account is not registered as a student");
        }

        if (student.getKeycloakId() != null
                && !student.getKeycloakId().equals(keycloakId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This email is linked to another Keycloak account");
        }

        student.setKeycloakId(keycloakId);

        return appUserRepository.save(student);
    }
}
