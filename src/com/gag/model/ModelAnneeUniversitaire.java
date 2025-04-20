package com.gag.model;

public class ModelAnneeUniversitaire {
    private int anneeUniversitaireId;
    private int debut;
    private int fin;
    private String libelle;

    public ModelAnneeUniversitaire() {
    }

    public ModelAnneeUniversitaire(int anneeUniversitaireId, int debut, int fin, String libelle) {
        this.anneeUniversitaireId = anneeUniversitaireId;
        this.debut = debut;
        this.fin = fin;
        this.libelle = libelle;
    }

    public int getAnneeUniversitaireId() {
        return anneeUniversitaireId;
    }

    public void setAnneeUniversitaireId(int anneeUniversitaireId) {
        this.anneeUniversitaireId = anneeUniversitaireId;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    @Override 
    public String toString() {
        return libelle;
    }
} 