package com.gag.model;

import java.util.Objects;

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

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ModelFiliere that = (ModelFiliere) obj;
        return filiereId == that.filiereId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filiereId);
    }
}