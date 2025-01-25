package com.example.RIS.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/", "/login", "/register").permitAll()  // Acceso público a estas URLs
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Acceso restringido a usuarios con rol ADMIN
                .requestMatchers("/turnos/**").hasRole("USER")  // Acceso restringido a usuarios con rol USER
                .anyRequest().authenticated()  // Resto de URLs requieren autenticación
                .and()
                .formLogin()
                .loginPage("/login")  // Página de login
                .permitAll()  // Permitir acceso público al login
                .and()
                .logout()
                .permitAll();  // Permitir logout público
        // Permitir que todos accedan al logout

        return http.build();  // Necesario en versiones recientes de Spring Security
    }

    // Definimos los usuarios en memoria con roles fijos
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(
                new User("user", passwordEncoder().encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))),
                new User("admin", passwordEncoder().encode("admin"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))
        );
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usamos BCrypt para encriptar contraseñas
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500") // El origen de tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
