package com.project.SoutienScolaire.repository;

import com.project.SoutienScolaire.modele.ProfRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfRequestRepository extends JpaRepository<ProfRequest, Long> {
    List<ProfRequest> findByMatieresNom(String matiere);

}
