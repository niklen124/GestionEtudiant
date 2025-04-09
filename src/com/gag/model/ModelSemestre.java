package com.gag.model;

public class ModelSemestre {
    private int semestreId;
    private String name;

    public ModelSemestre() {
    }

    public ModelSemestre(int semestreId, String name) {
        this.semestreId = semestreId;
        this.name = name;
    }

    public int getSemestreId() {
        return semestreId;
    }

    public void setSemestreId(int semestreId) {
        this.semestreId = semestreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} 