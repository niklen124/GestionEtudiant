package com.gag.model;

public class ModelFiliere {
    private int filiereId;
    private String name;
    private ModelDepartement departement;

    public ModelFiliere() {
    }

    public ModelFiliere(int filiereId, String name, ModelDepartement departement) {
        this.filiereId = filiereId;
        this.name = name;
        this.departement = departement;
    }

    public int getFiliereId() {
        return filiereId;
    }

    public void setFiliereId(int filiereId) {
        this.filiereId = filiereId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelDepartement getDepartement() {
        return departement;
    }

    public void setDepartement (ModelDepartement departement) {
        if (departement == null || departement.getName().isEmpty()) {
            throw new IllegalArgumentException("Le département est invalide.");
        }
        this.departement = departement;
    }
    
}