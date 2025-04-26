package com.gag.model;

import java.sql.Date;

public class ModelInscription {
    private int inscriptionId;
    private ModelEtudiant etudiant;
    private ModelModule module;
    private ModelAnneeUniversitaire anneeUniversitaire;

    public ModelInscription() {
    }

    public ModelInscription(int inscriptionId, ModelEtudiant etudiant, ModelModule module, ModelAnneeUniversitaire anneeUniversitaire) {
        this.inscriptionId = inscriptionId;
        this.etudiant = etudiant;
        this.module = module;
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public int getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(int inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public ModelEtudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(ModelEtudiant etudiant) {
        this.etudiant = etudiant;
    }

    public ModelModule getModule() {
        return module;
    }

    public void setModule(ModelModule module) {
        this.module = module;
    }

    public ModelAnneeUniversitaire getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(ModelAnneeUniversitaire anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }
} 