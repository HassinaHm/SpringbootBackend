package com.project.SoutienScolaire.controller;

import com.project.SoutienScolaire.modele.ProfRequest;
import com.project.SoutienScolaire.repository.ProfRequestRepository;
import com.project.SoutienScolaire.service.ProfRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/req/professeurs")
public class ProfRequestController {

    @Autowired
    private ProfRequestRepository profRequestRepository;
    @Autowired
    private ProfRequestService profRequestService;

    @GetMapping
    public List<ProfRequest> getAllProfesseurs() {
        return profRequestRepository.findAll();
    }

    @GetMapping("/matieres/{matiere}")
    public ResponseEntity<List<ProfRequest>> getProfesseursByMatiereNom(@PathVariable String matiere) {
        List<ProfRequest> professeurs = profRequestService.getProfesseursByMatiereNom(matiere);
        return ResponseEntity.ok(professeurs);
    }

    @PostMapping
    public ProfRequest addProfesseur(@RequestBody ProfRequest professeur) {
        return profRequestRepository.save(professeur);
    }

    @GetMapping("/{id}")
    public ProfRequest getProfesseurById(@PathVariable Long id) {
        return profRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur not found with id: " + id));
    }

    @PutMapping("/{id}")
    public ProfRequest updateProfesseur(@PathVariable Long id, @RequestBody ProfRequest professeurDetails) {
        ProfRequest professeur = profRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur not found with id: " + id));
        professeur.setNom(professeurDetails.getNom());
        professeur.setPrenom(professeurDetails.getPrenom());
        professeur.setEmail(professeurDetails.getEmail());
        professeur.setImageUrl(professeurDetails.getImageUrl());
        professeur.setNumberTel(professeurDetails.getNumberTel());
        professeur.setTarifh(professeurDetails.getTarifh());
        professeur.setMatiere(professeurDetails.getMatiere());
        professeur.setCv(professeurDetails.getCv());
        professeur.setDescription(professeurDetails.getDescription());
        return profRequestRepository.save(professeur);
    }

    @DeleteMapping("/{id}")
    public String deleteProfesseur(@PathVariable Long id) {
        profRequestRepository.deleteById(id);
        return "Professeur with id: " + id + " has been deleted successfully.";
    }

}
