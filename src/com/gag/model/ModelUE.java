package com.gag.model;

public class ModelUE {
    private int ueId;
    private String code;
    private String name;
    private int semestreId;
    private int filiereId;

    public ModelUE() {
    }

    public ModelUE(int ueId, String code, String name, int semestreId, int filiereId) {
        this.ueId = ueId;
        this.code = code;
        this.name = name;
        this.semestreId = semestreId;
        this.filiereId = filiereId;
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

    public int getSemestreId() {
        return semestreId;
    }

    public void setSemestreId(int semestreId) {
        this.semestreId = semestreId;
    }

    public int getFiliereId() {
        return filiereId;
    }

    public void setFiliereId(int filiereId) {
        this.filiereId = filiereId;
    }
} 