package com.project.SoutienScolaire.service;

import com.project.SoutienScolaire.modele.Matiere;
import com.project.SoutienScolaire.repository.MatiereRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;

    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    public Matiere getMatiereById(Long id) {
        return matiereRepository.findById(id).orElse(null);
    }

    public Matiere addMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public Matiere updateMatiere(Long id, Matiere matiere) {
        if (matiereRepository.existsById(id)) {
            matiere.setId(id);
            return matiereRepository.save(matiere);
        }
        return null; // Handle case where matiere with given id doesn't exist
    }

    public void deleteMatiere(Long id) {
        matiereRepository.deleteById(id);
    }
}
