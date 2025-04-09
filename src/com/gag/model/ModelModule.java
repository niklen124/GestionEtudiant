package com.gag.model;

public class ModelModule {
    private int moduleId;
    private String code;
    private String name;
    private int ueId;
    private int enseignantId;

    public ModelModule() {
    }

    public ModelModule(int moduleId, String code, String name, int ueId, int enseignantId) {
        this.moduleId = moduleId;
        this.code = code;
        this.name = name;
        this.ueId = ueId;
        this.enseignantId = enseignantId;
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

    public int getUeId() {
        return ueId;
    }

    public void setUeId(int ueId) {
        this.ueId = ueId;
    }

    public int getEnseignantId() {
        return enseignantId;
    }

    public void setEnseignantId(int enseignantId) {
        this.enseignantId = enseignantId;
    }
} 