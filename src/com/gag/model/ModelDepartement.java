package com.gag.model;

public class ModelDepartement {
    private int departementId;
    private String name;

    public ModelDepartement() {
    }

    public ModelDepartement(int departementId, String name) {
        this.departementId = departementId;
        this.name = name;
    }

    public int getDepartementId() {
        return departementId;
    }

    public void setDepartementId(int departementId) {
        this.departementId = departementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} 