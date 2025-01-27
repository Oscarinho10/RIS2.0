package com.example.RIS.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(InMemoryUserDetailsManager userDetailsService, PasswordEncoder passwordEncoder) {
        return args -> {

            // Initialize admin user if not present
            if (!userDetailsService.userExists("admin")) {
                User adminUser = new User("admin", passwordEncoder.encode("admin"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                userDetailsService.createUser(adminUser);
            }

            // Initialize regular user if not present
            if (!userDetailsService.userExists("user")) {
                User regularUser = new User("user", passwordEncoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
                userDetailsService.createUser(regularUser);
            }
        };
    }
}
