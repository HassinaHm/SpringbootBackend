package com.project.SoutienScolaire.repository;

import java.util.Optional;

import com.project.SoutienScolaire.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
