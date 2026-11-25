
package com.college.leavemanagement.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    HttpMethod.OPTIONS, "/**"
                ).permitAll()

                // Allow Swagger UI and API documentation
                .requestMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs",
                    "/v3/api-docs/**"
                ).permitAll()

                // Public authentication endpoints
                .requestMatchers("/api/auth/**").permitAll()

                // Admin-only endpoints
                .requestMatchers(
                    HttpMethod.GET,
                    "/api/leaves/admin"
                ).hasRole("ADMIN")

                .requestMatchers(
                    HttpMethod.PUT,
                    "/api/leaves/*/status"
                ).hasRole("ADMIN")

                // Leave APIs require authentication
                .requestMatchers("/api/leaves/**").authenticated()

                // All other requests retain authentication
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(
                    jwtAuthenticationConverter()
                ))
            );

        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends AbstractAuthenticationToken>
            jwtAuthenticationConverter() {

        return jwt -> {
            Collection<GrantedAuthority> authorities =
                new ArrayList<>();

            JwtGrantedAuthoritiesConverter scopeConverter =
                new JwtGrantedAuthoritiesConverter();

            Collection<GrantedAuthority> scopeAuthorities =
                scopeConverter.convert(jwt);

            if (scopeAuthorities != null) {
                authorities.addAll(scopeAuthorities);
            }

            Map<String, Object> realmAccess =
                jwt.getClaimAsMap("realm_access");

            if (realmAccess != null) {
                Object rolesObject = realmAccess.get("roles");

                if (rolesObject instanceof Collection<?> roles) {
                    for (Object role : roles) {
                        if ("ADMIN".equals(role)
                                || "STUDENT".equals(role)) {
                            authorities.add(
                                new SimpleGrantedAuthority(
                                    "ROLE_" + role
                                )
                            );
                        }
                    }
                }
            }

            String principalName =
                jwt.getClaimAsString("preferred_username");

            return new JwtAuthenticationToken(
                jwt,
                authorities,
                principalName != null
                    ? principalName
                    : jwt.getSubject()
            );
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration =
            new CorsConfiguration();

        configuration.setAllowedOrigins(
            List.of("http://localhost:5173")
        );

        configuration.setAllowedMethods(
            List.of(
                "GET", "POST", "PUT",
                "DELETE", "OPTIONS"
            )
        );

        configuration.setAllowedHeaders(
            List.of("Authorization", "Content-Type")
        );

        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
            "/**", configuration
        );

        return source;
    }
}
