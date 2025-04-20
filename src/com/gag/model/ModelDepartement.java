package com.gag.model;

import java.util.Objects;

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
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ModelDepartement that = (ModelDepartement) obj;
        return departementId == that.departementId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departementId);
    }
}