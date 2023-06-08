package com.tutorial.resourceserver.controller;

import com.tutorial.resourceserver.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceServerController {

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','OIDC_USER')") // OIDC_USER rol de google
    public ResponseEntity<MessageDto> user (Authentication authentication){
        return ResponseEntity.ok(new MessageDto("Hola "+authentication.getName() +" roles "+authentication.getAuthorities().toString()));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MessageDto> admin (Authentication authentication){
        return ResponseEntity.ok(new MessageDto("Hola admin "+authentication.getName() +" roles "+authentication.getAuthorities().toString()));
    }
}
