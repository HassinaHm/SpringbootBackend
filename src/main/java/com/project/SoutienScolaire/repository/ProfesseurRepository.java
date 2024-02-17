package com.project.SoutienScolaire.repository;

import com.project.SoutienScolaire.modele.Professeur;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    List<Professeur> findByMatieresNom(String matiere);

}
