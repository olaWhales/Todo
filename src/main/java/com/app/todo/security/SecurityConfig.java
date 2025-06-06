package com.app.todo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.app.todo", "com.app.todo.security"})
public class SecurityConfig {
//    public SecurityConfig() {
//        System.out.println("SecurityConfig instance created");
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        System.out.println("Configuring SecurityFilterChain");
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/public/**").permitAll()
//                        .requestMatchers("/api/user").authenticated()
                        .requestMatchers("/admin/**").hasRole("admin")
                        .requestMatchers("/student/**").hasRole("student")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder())
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/protected", true)
                        .failureUrl("/login?error") // Redirect on failure for debugging
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                );
        return http.build();
    }
@Bean
public JwtDecoder jwtDecoder() {
    String issuerUri = "http://localhost:8080/realms/Olawale";
    String jwkSetUri = issuerUri + "/protocol/openid-connect/certs";
    NimbusJwtDecoder decoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri)
            .build();
    decoder.setJwtValidator(JwtValidators.createDefaultWithIssuer(issuerUri));
    System.out.println("JwtDecoder initialized with issuer: " + issuerUri);
    return decoder;
}
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        System.out.println("JwtAuthenticationConverter initialized");
        JwtAuthenticationConverter converter =  new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {

            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            if(resourceAccess == null || !resourceAccess.containsKey("todoApp")) {
                return List.of(); // No roles
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> todoAppAccess = (Map<String, Object>) resourceAccess.get("todoApp");
            Object rolesObj = todoAppAccess.get("roles");

            List<String> roles = new ArrayList<>();
            if (rolesObj instanceof List<?>) {
                for (Object role : (List<?>) rolesObj) {
                    if (role instanceof String) {
                        roles.add((String) role);
                    }
                }
            }


            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());
        });

        return converter;
    }
}

