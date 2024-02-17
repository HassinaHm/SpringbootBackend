package com.project.SoutienScolaire.dto;

import com.project.SoutienScolaire.modele.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;

    public String getNom() {
      return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
      }

      public String getEmail() {
        return this.email;
      }

}