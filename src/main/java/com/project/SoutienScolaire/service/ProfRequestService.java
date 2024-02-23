package com.project.SoutienScolaire.service;

import com.project.SoutienScolaire.modele.ProfRequest;
import com.project.SoutienScolaire.repository.ProfRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfRequestService {
    @Autowired
    private ProfRequestRepository profReqRepository;

    public List<ProfRequest> getAllProfesseurs() {
        return profReqRepository.findAll();
    }

    public List<ProfRequest> getProfesseursByMatiereNom(String matiere) {
        return profReqRepository.findByMatieresNom(matiere);
    }

    public ProfRequest getProfesseurById(Long id) {
        return profReqRepository.findById(id).orElse(null);
    }

    public ProfRequest saveProfesseur(ProfRequest professeur) {
        return profReqRepository.save(professeur);
    }

    public ProfRequest updateProfesseur(Long id, ProfRequest professeur) {
        ProfRequest existingProfesseur = profReqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé avec l'ID : " + id));

        existingProfesseur.setNom(professeur.getNom());
        existingProfesseur.setPrenom(professeur.getPrenom());

        return profReqRepository.save(existingProfesseur);
    }

    public void deleteProfesseur(Long id) {
        profReqRepository.deleteById(id);
    }

}
