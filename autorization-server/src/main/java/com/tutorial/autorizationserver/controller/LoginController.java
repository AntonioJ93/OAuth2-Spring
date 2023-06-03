package com.tutorial.autorizationserver.controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @PostMapping
    public String logoutOk(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.logout().logoutSuccessUrl("login?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true);
        return "login?logout";
    }

}
