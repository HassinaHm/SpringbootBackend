package com.project.SoutienScolaire.controller;

import com.project.SoutienScolaire.modele.Professeur;
import com.project.SoutienScolaire.repository.ProfesseurRepository;
import com.project.SoutienScolaire.service.ProfesseurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professeurs")
public class ProfesseurController {

    @Autowired
    private ProfesseurRepository professeurRepository;
    @Autowired
    private ProfesseurService professeurService;

    @GetMapping
    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    @GetMapping("/matieres/{matiere}")
    public ResponseEntity<List<Professeur>> getProfesseursByMatiereNom(@PathVariable String matiere) {
        List<Professeur> professeurs = professeurService.getProfesseursByMatiereNom(matiere);
        return ResponseEntity.ok(professeurs);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Professeur addProfesseur(@RequestBody Professeur professeur) {
        return professeurRepository.save(professeur);
    }

    @GetMapping("/{id}")
    public Professeur getProfesseurById(@PathVariable Long id) {
        return professeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur not found with id: " + id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Professeur updateProfesseur(@PathVariable Long id, @RequestBody Professeur professeurDetails) {
        Professeur professeur = professeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur not found with id: " + id));
        professeur.setNom(professeurDetails.getNom());
        professeur.setPrenom(professeurDetails.getPrenom());
        professeur.setEmail(professeurDetails.getEmail());
        professeur.setImageUrl(professeurDetails.getImageUrl());
        professeur.setNumberTel(professeurDetails.getNumberTel());
        professeur.setTarifh(professeurDetails.getTarifh());
        professeur.setCv(professeurDetails.getCv());
        professeur.setMessage(professeurDetails.getMessage());
        return professeurRepository.save(professeur);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProfesseur(@PathVariable Long id) {
        professeurRepository.deleteById(id);
        return "Professeur with id: " + id + " has been deleted successfully.";
    }

}
