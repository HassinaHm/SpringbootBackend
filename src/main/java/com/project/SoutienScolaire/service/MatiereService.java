package com.project.SoutienScolaire.service;

import com.project.SoutienScolaire.modele.Matiere;

import java.util.List;

public interface MatiereService {
    List<Matiere> getAllMatieres();

    Matiere getMatiereById(Long id);

    Matiere addMatiere(Matiere matiere);

    Matiere updateMatiere(Long id, Matiere matiere);

    void deleteMatiere(Long id);
}
