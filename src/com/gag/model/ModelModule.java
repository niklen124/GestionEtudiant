package com.gag.model;

public class ModelModule {
    private int moduleId;
    private String code;
    private String name;
    private ModelUE ue;
    private ModelEnseignant enseignant;

    public ModelModule() {
    }

    public ModelModule(int moduleId, String code, String name, ModelUE ue, ModelEnseignant enseignant) {
        this.moduleId = moduleId;
        this.code = code;
        this.name = name;
        this.ue = ue;
        this.enseignant = enseignant;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
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

    public ModelUE getUe() {
        return ue;
    }

    public void setUe(ModelUE ue) {
        this.ue = ue;
    }

    public ModelEnseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(ModelEnseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Object[]toTableRow(int rowNum) {
        return new Object[]{false,rowNum,this,name,ue,enseignant};
    }

    @Override
    public String toString() {
        return code;
    }
} 