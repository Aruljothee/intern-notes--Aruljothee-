
package com.college.leavemanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.college.leavemanagement.service.KeycloakUserService;

@Component
@ConditionalOnProperty(
    name = "app.bootstrap-admin.enabled",
    havingValue = "true"
)
public class AdminUserInitializer implements ApplicationRunner {

    private final KeycloakUserService keycloakUserService;

    @Value("${app.bootstrap-admin.first-name}")
    private String firstName;

    @Value("${app.bootstrap-admin.last-name}")
    private String lastName;

    @Value("${app.bootstrap-admin.email}")
    private String email;

    @Value("${app.bootstrap-admin.password}")
    private String password;

    public AdminUserInitializer(
            KeycloakUserService keycloakUserService) {

        this.keycloakUserService = keycloakUserService;
    }

    @Override
    public void run(ApplicationArguments args) {

        System.out.println(
            "Starting initial Admin account provisioning..."
        );

        try {

            String userId = keycloakUserService.createAdmin(
                firstName,
                lastName,
                email,
                password
            );

            System.out.println(
                "Admin account created successfully!"
            );

            System.out.println(
                "Admin username: admin1"
            );

            System.out.println(
                "Keycloak User ID: " + userId
            );

        } catch (Exception e) {

            throw new IllegalStateException(
                "Initial Admin provisioning failed. "
                + "Check Keycloak configuration and logs.",
                e
            );
        }
    }
}
