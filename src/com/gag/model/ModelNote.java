package com.gag.model;

public class ModelNote {
    private int noteId;
    private double note;
    private ModelEtudiant etudiant;
    private ModelModule module;
    private ModelAnneeUniversitaire anneeUniversitaire;
    private ModelInscription inscription;

    public ModelNote() {
    }

    public ModelNote(int noteId, double note, ModelEtudiant etudiant, ModelModule module, ModelAnneeUniversitaire anneeUniversitaire, ModelInscription inscription) {
        this.noteId = noteId;
        this.note = note;
        this.etudiant = etudiant;
        this.module = module;
        this.anneeUniversitaire = anneeUniversitaire;
        this.inscription = inscription;
    }

    public int getNoteId() {
        return noteId;
    }
    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public double getNote() {
        return note;
    }
    public void setNote(double note) {
        this.note = note;
    }

    public ModelEtudiant getEtudiant() {
        return etudiant;
    }
    public void setEtudiant(ModelEtudiant etudiant) {
        this.etudiant = etudiant;
    }

    public ModelModule getModule() {
        return module;
    }
    public void setModule(ModelModule module) {
        this.module = module;
    }

    public ModelAnneeUniversitaire getAnneeUniversitaire() {
        return anneeUniversitaire;
    }
    public void setAnneeUniversitaire(ModelAnneeUniversitaire anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public ModelInscription getInscription() {
        return inscription;
    }
    public void setInscription(ModelInscription inscription) {
        this.inscription = inscription;
    }

    // Nouvelle méthode pour obtenir l'ID de l'inscription
    public int getInscriptionId() {
        if (inscription != null) {
            return inscription.getInscriptionId();
        }
        throw new IllegalStateException("L'inscription est null pour cette note.");
    }

    // Nouvelle méthode pour définir l'ID de l'inscription
    public void setInscriptionId(int inscriptionId) {
        if (this.inscription == null) {
            this.inscription = new ModelInscription();
        }
        this.inscription.setInscriptionId(inscriptionId);
    }
}
