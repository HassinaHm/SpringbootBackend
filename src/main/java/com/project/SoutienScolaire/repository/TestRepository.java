package com.project.SoutienScolaire.repository;

import com.project.SoutienScolaire.modele.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

}
