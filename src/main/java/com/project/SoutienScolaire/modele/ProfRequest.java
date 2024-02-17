package com.project.SoutienScolaire.modele;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProfRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    @Column(nullable = true)
    private String imageUrl;
    @Column(nullable = true)
    private Integer numberTel;
    @Column(nullable = true)
    private Integer tarifh;
    @Column(nullable = true)
    private String cv;
    @Column(nullable = true)
    private String description;

    public ProfRequest() {
    }

    public ProfRequest(String nom, String prenom, String email, String imageUrl, Integer numberTel, Integer tarifh,
            String cv, String description, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.imageUrl = imageUrl;
        this.numberTel = numberTel;
        this.tarifh = tarifh;
        this.cv = cv;
        this.description = description;
        this.password = password;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Professeur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", numberTel=" + numberTel +
                ", tarifh=" + tarifh +
                ", cv='" + cv + '\'' +
                ", message='" + description + '\'' +
                '}';
    }
}
