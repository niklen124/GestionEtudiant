package com.gag.model;

public class ModelEnseignant {
    private int enseignantId;
    private String name;
    private String userName;
    private String email;
    private String grade;
    private int departementId;

    public ModelEnseignant() {
    }

    public ModelEnseignant(int enseignantId, String name, String userName, String email, String grade, int departementId) {
        this.enseignantId = enseignantId;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.grade = grade;
        this.departementId = departementId;
    }

    public int getEnseignantId() {
        return enseignantId;
    }

    public void setEnseignantId(int enseignantId) {
        this.enseignantId = enseignantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getDepartementId() {
        return departementId;
    }

    public void setDepartementId(int departementId) {
        this.departementId = departementId;
    }
} 