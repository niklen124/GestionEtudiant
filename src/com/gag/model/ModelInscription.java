package com.gag.model;

import java.sql.Date;

public class ModelInscription {
    private int inscriptionId;
    private int etudiantId;
    private int moduleId;
    private int anneeUniversitaireId;
    private Date dateInscription;

    public ModelInscription() {
    }

    public ModelInscription(int inscriptionId, int etudiantId, int moduleId, int anneeUniversitaireId, Date dateInscription) {
        this.inscriptionId = inscriptionId;
        this.etudiantId = etudiantId;
        this.moduleId = moduleId;
        this.anneeUniversitaireId = anneeUniversitaireId;
        this.dateInscription = dateInscription;
    }

    public int getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(int inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public int getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(int etudiantId) {
        this.etudiantId = etudiantId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getAnneeUniversitaireId() {
        return anneeUniversitaireId;
    }

    public void setAnneeUniversitaireId(int anneeUniversitaireId) {
        this.anneeUniversitaireId = anneeUniversitaireId;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
} 