package com.tutorial.autorizationserver.repository;

import com.tutorial.autorizationserver.entity.GoogleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoogleUserRepository extends JpaRepository<GoogleUser,Integer> {

    Optional<GoogleUser> findByEmail(String email);
}
