package com.example.RIS.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Método para cargar el usuario desde la base de datos
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aquí debes implementar la lógica para cargar el usuario desde la base de datos
        if ("user".equals(username)) {
            return User.builder()
                    .username("user")
                    .password("$2a$10$E4JbF3zF5f3F1r1fz34IuuaLV3jj64W9D.qytv3g4mkeCckvGDRyW")  // Contraseña encriptada (password: password)
                    .roles("USER")
                    .build();
        } else if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
                    .password("$2a$10$6ZMkKHmb8bxZJZXl.J6izYzBeZx0AWsS8Gn1mjDmfO3QwSBZpaHSq")  // Contraseña encriptada (password: admin)
                    .roles("ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("Usuario no encontrado");
    }
}
