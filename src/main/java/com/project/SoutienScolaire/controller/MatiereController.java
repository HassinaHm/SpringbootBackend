package com.project.SoutienScolaire.controller;

import com.project.SoutienScolaire.modele.Matiere;
import com.project.SoutienScolaire.service.MatiereService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matieres")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    @GetMapping
    public ResponseEntity<List<Matiere>> getAllMatieres() {
        List<Matiere> matieres = matiereService.getAllMatieres();
        return new ResponseEntity<>(matieres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getMatiereById(@PathVariable Long id) {
        Matiere matiere = matiereService.getMatiereById(id);
        return new ResponseEntity<>(matiere, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Matiere> addMatiere(@RequestBody Matiere matiere) {
        Matiere newMatiere = matiereService.addMatiere(matiere);
        return new ResponseEntity<>(newMatiere, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Matiere> updateMatiere(@PathVariable Long id, @RequestBody Matiere matiere) {
        Matiere updatedMatiere = matiereService.updateMatiere(id, matiere);
        return new ResponseEntity<>(updatedMatiere, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMatiere(@PathVariable Long id) {
        matiereService.deleteMatiere(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
