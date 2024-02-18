package com.project.SoutienScolaire.Config;

import com.project.SoutienScolaire.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.project.SoutienScolaire.modele.Permission.*;
import static com.project.SoutienScolaire.modele.Role.ADMIN;
import static com.project.SoutienScolaire.modele.Role.MANAGER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

        private static final String[] WHITE_LIST_URL = { "/api/v1/auth/**",
                        "/api/v1/auth/register",
                        "/api/v1/auth/authenticate",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html" };
        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;
        private final LogoutHandler logoutHandler;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URL)
                                                .permitAll()
                                                .requestMatchers(POST, "/api/v1/auth/authenticate/**").permitAll()
                                                .requestMatchers(GET, "/api/v1/auth/authenticate/**").permitAll()

                                                .requestMatchers(POST, " /api/v1/auth/register/**").permitAll()
                                                .requestMatchers(GET, " /api/v1/auth/register/**").permitAll()

                                                .requestMatchers(POST, "/api/images/upload/**").permitAll()
                                                .requestMatchers(POST, "/register/**").permitAll()
                                                .requestMatchers(GET, "/api/professeurs/**").permitAll()
                                                .requestMatchers(GET, "/api/etudiants/**").permitAll()
                                                .requestMatchers(POST, "/api/etudiants/**").permitAll()
                                                .requestMatchers(DELETE, "/api/etudiants/**").permitAll()
                                                .requestMatchers(PUT, "/api/etudiants/**").permitAll()
                                                .requestMatchers(POST, "/api/professeurs/**").permitAll()
                                                .requestMatchers(DELETE, "/api/professeurs/**").permitAll()
                                                .requestMatchers(PUT, "/api/professeurs/**").permitAll()
                                                .requestMatchers(GET, "/api/images/**").permitAll()
                                                .requestMatchers(GET, "/matieres/**").permitAll()
                                                .requestMatchers(POST, "/matieres/**").permitAll()
                                                .requestMatchers(GET, "/api/images/**").permitAll()
                                                .requestMatchers("/api/v1/management/**")
                                                .hasAnyRole(ADMIN.name(), MANAGER.name())
                                                .requestMatchers(GET, "/api/v1/management/**")
                                                .hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                                                .requestMatchers(POST, "/api/v1/management/**")
                                                .hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                                                .requestMatchers(PUT, "/api/v1/management/**")
                                                .hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                                                .requestMatchers(DELETE, "/api/v1/management/**")
                                                .hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                                                .anyRequest()
                                                .authenticated())
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .logout(logout -> logout.logoutUrl("/api/v1/auth/logout")
                                                .addLogoutHandler(logoutHandler)
                                                .logoutSuccessHandler((request, response,
                                                                authentication) -> SecurityContextHolder
                                                                                .clearContext()));

                return http.build();
        }
}