package com.gag.model;

import java.sql.Date;

public class ModelEtudiant {
    private int etudiantId;
    private String matricule;
    private String name;
    private String userName;
    private String email;
    private String telephone;
    private Date dateNaissance;
    private String sexe;
    private ModelAnneeUniversitaire anneeUniversitaire;
    private ModelFiliere filiere;

    public ModelEtudiant() {
    }

    public ModelEtudiant(int etudiantId, String matricule, String name, String userName, String email, 
                        String telephone, Date dateNaissance, String sexe, ModelAnneeUniversitaire anneeUniversitaire, ModelFiliere filiere) {
        this.etudiantId = etudiantId;
        this.matricule = matricule;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.anneeUniversitaire = anneeUniversitaire;
        this.filiere = filiere;
    }

    public int getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(int etudiantId) {
        this.etudiantId = etudiantId;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public ModelAnneeUniversitaire getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(ModelAnneeUniversitaire anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public ModelFiliere getFiliere() {
        return filiere;
    }

    public void setFiliere(ModelFiliere filiere) {
        this.filiere = filiere;
    }
} 