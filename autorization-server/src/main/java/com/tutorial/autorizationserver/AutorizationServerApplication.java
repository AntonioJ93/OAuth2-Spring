package com.tutorial.autorizationserver;

import com.tutorial.autorizationserver.entity.Role;
import com.tutorial.autorizationserver.enums.RoleName;
import com.tutorial.autorizationserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutorizationServerApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(AutorizationServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(roleRepository.findAll().isEmpty()) {
			//poblamos
			roleRepository.save(
					Role.builder()
							.roleName(RoleName.ROLE_ADMIN)
							.build()
			);
			roleRepository.save(
					Role.builder()
							.roleName(RoleName.ROLE_USER)
							.build()
			);
		}
	}
}
