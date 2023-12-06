package CapstoneProject.CapstoneProject.security;

import CapstoneProject.CapstoneProject.security.CustomFilter;
import CapstoneProject.CapstoneProject.security.FilterExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class FilterChainConfig {
    @Autowired
    private CustomFilter customFilter;
    @Autowired
    private FilterExceptions filterExceptions;
    @Bean
    SecurityFilterChain securityFilterChainConfig(HttpSecurity http) throws Exception {
        try {
            http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
            http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable());

            http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
            http.addFilterBefore(filterExceptions, CustomFilter.class);
            http.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());
            http.cors(withDefaults());
            return http.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
