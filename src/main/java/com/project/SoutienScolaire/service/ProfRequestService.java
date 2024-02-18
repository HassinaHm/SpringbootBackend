package com.project.SoutienScolaire.service;

import com.project.SoutienScolaire.modele.ProfRequest;
import com.project.SoutienScolaire.repository.ProfRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfRequestService {
    @Autowired
    private ProfRequestRepository professeurRepository;

    public List<ProfRequest> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    public List<ProfRequest> getProfesseursByMatiereNom(String matiere) {
        return professeurRepository.findByMatieresNom(matiere);
    }

    public ProfRequest getProfesseurById(Long id) {
        return professeurRepository.findById(id).orElse(null);
    }

    public ProfRequest saveProfesseur(ProfRequest professeur) {
        return professeurRepository.save(professeur);
    }

    public ProfRequest updateProfesseur(Long id, ProfRequest professeur) {
        ProfRequest existingProfesseur = professeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé avec l'ID : " + id));

        existingProfesseur.setNom(professeur.getNom());
        existingProfesseur.setPrenom(professeur.getPrenom());

        return professeurRepository.save(existingProfesseur);
    }

    public void deleteProfesseur(Long id) {
        professeurRepository.deleteById(id);
    }

}
