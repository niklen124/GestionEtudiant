package com.gag.model;

public class ModelEmploiDuTemps {
    private int coursId;
    private int moduleId;
    private int enseignantId;
    private String jour;
    private String heureDebut;
    private String heureFin;
    private String salle;

    public ModelEmploiDuTemps() {
    }

    public ModelEmploiDuTemps(int coursId, int moduleId, int enseignantId, String jour, 
                             String heureDebut, String heureFin, String salle) {
        this.coursId = coursId;
        this.moduleId = moduleId;
        this.enseignantId = enseignantId;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.salle = salle;
    }

    public int getCoursId() {
        return coursId;
    }

    public void setCoursId(int coursId) {
        this.coursId = coursId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getEnseignantId() {
        return enseignantId;
    }

    public void setEnseignantId(int enseignantId) {
        this.enseignantId = enseignantId;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }
} 