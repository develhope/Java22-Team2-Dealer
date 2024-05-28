package com.develhope.spring.Features.Autentication.Confing;

import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.GET, "/acquirente/**").hasAnyAuthority(Role.ACQUIRENTE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.POST, "/acquirente/**").hasAnyAuthority(Role.ACQUIRENTE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.PUT, "/acquirente/**").hasAnyAuthority(Role.ACQUIRENTE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.DELETE, "/acquirente/**").hasAnyAuthority(Role.ACQUIRENTE.name(), Role.AMMINISTRATORE.name())

                        .requestMatchers(HttpMethod.GET, "/venditore/**").hasAnyAuthority(Role.VENDITORE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.POST, "/venditore/**").hasAnyAuthority(Role.VENDITORE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.PUT, "/venditore/**").hasAnyAuthority(Role.VENDITORE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.DELETE, "/venditore/**").hasAnyAuthority(Role.VENDITORE.name(), Role.AMMINISTRATORE.name())

                        .requestMatchers(HttpMethod.GET, "/veicolo/**").hasAnyAuthority(Role.VENDITORE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.POST, "/veicolo/**").hasAnyAuthority(Role.VENDITORE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.PUT, "/veicolo/**").hasAnyAuthority(Role.VENDITORE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.DELETE, "/veicolo/**").hasAnyAuthority(Role.VENDITORE.name(), Role.AMMINISTRATORE.name())

                        .requestMatchers(HttpMethod.GET, "/noleggio/**").hasAnyAuthority(Role.ACQUIRENTE.name(), Role.VENDITORE.name())
                        .requestMatchers(HttpMethod.POST, "/noleggio/**").hasAnyAuthority(Role.ACQUIRENTE.name(), Role.VENDITORE.name())
                        .requestMatchers(HttpMethod.PUT, "/noleggio/**").hasAnyAuthority(Role.ACQUIRENTE.name(), Role.VENDITORE.name())
                        .requestMatchers(HttpMethod.DELETE, "/noleggio/**").hasAnyAuthority(Role.ACQUIRENTE.name(), Role.VENDITORE.name())
                        .requestMatchers(HttpMethod.PATCH, "/noleggio/**").hasAnyAuthority(Role.VENDITORE.name())
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}