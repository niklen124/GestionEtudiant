package com.gag.model;

public class ModelFiliere {
    private int filiereId;
    private String name;
    private int departementId;

    public ModelFiliere() {
    }

    public ModelFiliere(int filiereId, String name, int departementId) {
        this.filiereId = filiereId;
        this.name = name;
        this.departementId = departementId;
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

    public int getDepartementId() {
        return departementId;
    }

    public void setDepartementId(int departementId) {
        this.departementId = departementId;
    }
} 