package com.example.RIS.turno.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Vista de login
    @GetMapping("/login")
    public String login() {
        return "login";  // Esta es la vista login.html
    }
}
