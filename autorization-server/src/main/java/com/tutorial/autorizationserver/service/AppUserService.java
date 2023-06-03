package com.tutorial.autorizationserver.service;


import com.tutorial.autorizationserver.enums.RoleName;
import com.tutorial.autorizationserver.dto.CreateAppUserDto;
import com.tutorial.autorizationserver.dto.MessageDto;
import com.tutorial.autorizationserver.entity.AppUser;
import com.tutorial.autorizationserver.entity.Role;
import com.tutorial.autorizationserver.repository.AppUserRepository;
import com.tutorial.autorizationserver.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService {

    private final AppUserRepository appUserRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public MessageDto createUser(CreateAppUserDto createAppUserDto){
        AppUser appUser= AppUser.builder()
                .username(createAppUserDto.username())
                .password(passwordEncoder.encode(createAppUserDto.password()))
                .build();
        Set<Role> roles = new HashSet<>();
        createAppUserDto.roles().forEach(role -> {
            Role rol= roleRepository.findByRoleName(RoleName.valueOf(role))
                    .orElseThrow(()->new RuntimeException("El rol no existe"));
            roles.add(rol);
        });
        appUser.setRoles(roles);
        appUserRepository.save(appUser);
        return new MessageDto("Usuario "+appUser.getUsername()+" creado correctamente");
    }
}
