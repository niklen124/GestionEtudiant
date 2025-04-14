package com.gag.model;

public class ModelEnseignant {
    private int enseignantId;
    private String name;
    private String userName;
    private String email;
    private String grade;
    private ModelDepartement departement;

    public ModelEnseignant() {
    }

    public ModelEnseignant(int enseignantId, String name, String userName, String email, String grade, ModelDepartement departement) {
        this.enseignantId = enseignantId;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.grade = grade;
        this.departement = departement;
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

    public ModelDepartement getDepartement() {
        return departement;
    }

    public void setDepartement (ModelDepartement departement) {
        this.departement = departement;
    }
    
    public Object[]toTableRow(int rowNum) {
        return new Object[]{false,rowNum,this,userName,email,grade,departement};
    }
    
    @Override
    public String toString() {
        return name;
    }
} 