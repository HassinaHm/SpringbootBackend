package com.project.SoutienScolaire.controller;

import com.project.SoutienScolaire.modele.Professeur;
import com.project.SoutienScolaire.repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professeurs")
public class ProfesseurController {

    @Autowired
    private ProfesseurRepository professeurRepository;

    @GetMapping
    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    @PostMapping
    public Professeur addProfesseur(@RequestBody Professeur professeur) {
        return professeurRepository.save(professeur);
    }

    @GetMapping("/{id}")
    public Professeur getProfesseurById(@PathVariable Long id) {
        return professeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur not found with id: " + id));
    }

    @PutMapping("/{id}")
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
    public String deleteProfesseur(@PathVariable Long id) {
        professeurRepository.deleteById(id);
        return "Professeur with id: " + id + " has been deleted successfully.";
    }
}
