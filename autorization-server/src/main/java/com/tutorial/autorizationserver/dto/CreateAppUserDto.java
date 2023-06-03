package com.tutorial.autorizationserver.dto;

import com.tutorial.autorizationserver.entity.Role;

import java.util.List;

public record CreateAppUserDto (

     String username,

     String password,

     List<String> roles
){}

