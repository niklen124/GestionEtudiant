package com.gag.model;

public class ModelUE {
    private int ueId;
    private String code;
    private String name;
    private ModelSemestre semestre;
    private ModelFiliere filiere;

    public ModelUE() {
    }

    public ModelUE(int ueId, String code, String name, ModelSemestre semestre, ModelFiliere filiere) {
        this.ueId = ueId;
        this.code = code;
        this.name = name;
        this.semestre = semestre;
        this.filiere = filiere;
    }

    public int getUeId() {
        return ueId;
    }

    public void setUeId(int ueId) {
        this.ueId = ueId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelSemestre getSemestre() {
        return semestre;
    }

    public void setSemestre(ModelSemestre semestre) {
        this.semestre = semestre;
    }

    public ModelFiliere getFiliere() {
        return filiere;
    }

    public void setFiliere(ModelFiliere filiere) {
        this.filiere = filiere;
    }
} 