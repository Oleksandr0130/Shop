package de.ait_tr.shop.security.security_config;

import de.ait_tr.shop.security.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenFilter tokenFilter;

    public SecurityConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
//                                .anyRequest().permitAll()
                                .requestMatchers(HttpMethod.GET,"/hello").permitAll()
                                .requestMatchers(HttpMethod.POST,"/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/refresh").permitAll()
                                .requestMatchers("//swagger-ui/**", "/v3/api-docs").permitAll()
                                .requestMatchers(HttpMethod.GET, "/products").permitAll()
                                .requestMatchers(HttpMethod.GET, "/products/{id}").authenticated()
//                        .requestMatchers(HttpMethod.GET, "/products/{id}").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
