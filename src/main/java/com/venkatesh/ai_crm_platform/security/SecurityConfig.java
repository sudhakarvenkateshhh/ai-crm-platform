package com.venkatesh.ai_crm_platform.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/register",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // ADMIN
                        .requestMatchers("/api/users/**")
                        .hasRole("ADMIN")

                        // SALES
                        .requestMatchers(
                                "/api/customers/**",
                                "/api/products/**",
                                "/api/orders/**"
                        ).hasAnyRole("ADMIN", "SALES")

                        // SUPPORT
                        .requestMatchers(
                                "/api/tickets/**",
                                "/api/comments/**"
                        ).hasAnyRole("ADMIN", "SUPPORT")

                        // MARKETING
                        .requestMatchers(
                                "/api/campaigns/**",
                                "/api/emails/**",
                                "/api/notifications/**"
                        ).hasAnyRole("ADMIN", "MARKETING")

                        // MANAGER
                        .requestMatchers("/api/dashboard/**")
                        .hasAnyRole("ADMIN", "MANAGER")

                        // Everything else requires login
                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class)

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }
}