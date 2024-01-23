package com.project.SoutienScolaire.service;

import com.project.SoutienScolaire.modele.Professeur;
import com.project.SoutienScolaire.repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurService {
    @Autowired
    private ProfesseurRepository professeurRepository;

    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    public Professeur getProfesseurById(Long id) {
        return professeurRepository.findById(id).orElse(null);
    }

    public Professeur saveProfesseur(Professeur professeur) {
        return professeurRepository.save(professeur);
    }

    public Professeur updateProfesseur(Long id, Professeur professeur) {
        Professeur existingProfesseur = professeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé avec l'ID : " + id));

        existingProfesseur.setNom(professeur.getNom());
        existingProfesseur.setPrenom(professeur.getPrenom());

        return professeurRepository.save(existingProfesseur);
    }

    public void deleteProfesseur(Long id) {
        professeurRepository.deleteById(id);
    }
}
