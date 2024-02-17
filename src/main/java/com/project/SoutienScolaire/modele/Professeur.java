package com.project.SoutienScolaire.modele;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String imageUrl;
    private String matiere;
    private Integer numberTel;
    private Integer tarifh;
    private String cv;
    @Column(nullable = true)
    private String message;

    @ManyToMany
    @JoinTable(name = "professeur_matiere", joinColumns = @JoinColumn(name = "professeur_id"), inverseJoinColumns = @JoinColumn(name = "matiere_id"))
    private Set<Matiere> matieres = new HashSet<>();

    public Professeur() {
    }

    public Professeur(String nom, String prenom, String email, String imageUrl, String matiere, Integer numberTel,
            Integer tarifh, String cv, String message) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.imageUrl = imageUrl;
        this.matiere = matiere;
        this.numberTel = numberTel;
        this.tarifh = tarifh;
        this.cv = cv;
        this.message = message;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public Integer getNumberTel() {
        return numberTel;
    }

    public void setNumberTel(Integer numberTel) {
        this.numberTel = numberTel;
    }

    public Integer getTarifh() {
        return tarifh;
    }

    public void setTarifh(Integer tarifh) {
        this.tarifh = tarifh;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<Matiere> getMatieres() {
        return matieres;
    }

    public void setMatieres(Set<Matiere> matieres) {
        this.matieres = matieres;
    }

    @Override
    public String toString() {
        return "Professeur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", matiere='" + matiere + '\'' +
                ", numberTel=" + numberTel +
                ", tarifh=" + tarifh +
                ", cv='" + cv + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
