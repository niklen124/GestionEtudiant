package com.gag.component;

import com.gag.model.ModelAnneeUniversitaire;
import com.gag.model.ModelDepartement;
import com.gag.model.ModelEnseignant;
import com.gag.model.ModelFiliere;
import com.gag.model.ModelSemestre;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import com.gag.model.ModelUE;
import com.gag.model.ModelModule;
import com.gag.model.ModelNote;
import com.gag.service.ServiceEtudiant;
import com.gag.model.ModelEtudiant;
import com.gag.service.ServiceEnseignant;
import com.gag.service.ServiceModule;
import com.gag.service.ServiceSaisirNotes;
import raven.toast.Notifications;
import java.text.ParseException;

public class CreateSaisirNote extends javax.swing.JPanel {

    public CreateSaisirNote() {
        initComponents();
        initListeners();
    }

    public void loadData(ServiceSaisirNotes serviceSaisirNotes) {
        try {
            // Charger les départements
            txtDepartement.removeAllItems();
            for (ModelDepartement pos : serviceSaisirNotes.getServiceDepartement().getAllDepartements()) {
                txtDepartement.addItem(pos);
            }

            // Charger les filières
            txtFiliere.removeAllItems();
            for (ModelFiliere pos : serviceSaisirNotes.getServiceFiliere().getAllFilieres()) {
                txtFiliere.addItem(pos);
            }

            // Charger les semestres
            txtSemestre.removeAllItems();
            for (ModelSemestre pos : serviceSaisirNotes.getServiceSemestre().getAllSemestres()) {
                txtSemestre.addItem(pos);
            }

            // Charger les années scolaires
            txtAnneeScolaire.removeAllItems();
            for (ModelAnneeUniversitaire pos : serviceSaisirNotes.getServiceAnneeUniversitaire().getAllAnneesUniversitaires()) {
                txtAnneeScolaire.addItem(pos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData(ServiceSaisirNotes serviceSaisirNotes, ModelNote note) {
        try {
            // Charger les départements
            txtDepartement.removeAllItems();
            for (ModelDepartement pos : serviceSaisirNotes.getServiceDepartement().getAllDepartements()) {
                txtDepartement.addItem(pos);
            }

            // Charger les filières
            txtFiliere.removeAllItems();
            for (ModelFiliere pos : serviceSaisirNotes.getServiceFiliere().getAllFilieres()) {
                txtFiliere.addItem(pos);
            }

            // Charger les semestres
            txtSemestre.removeAllItems();
            for (ModelSemestre pos : serviceSaisirNotes.getServiceSemestre().getAllSemestres()) {
                txtSemestre.addItem(pos);
            }

            // Charger les années scolaires
            txtAnneeScolaire.removeAllItems();
            for (ModelAnneeUniversitaire pos : serviceSaisirNotes.getServiceAnneeUniversitaire().getAllAnneesUniversitaires()) {
                txtAnneeScolaire.addItem(pos);
            }

            // Pré-remplir les champs avec les données de la note
            txtNomEtudiant.setText(note.getEtudiant().getName());
            txtCodeModule.setText(note.getModule().getCode());
            txtNomModule.setText(note.getModule().getName());
            txtCodeUE.setText(note.getModule().getUe().getCode());
            txtNomUE.setText(note.getModule().getUe().getName());
            txtNomEnseignant.setText(note.getModule().getEnseignant() != null ? note.getModule().getEnseignant().getName() : "");
            txtNote.setValue(note.getNote());

            // Sélectionner les éléments dans les combobox
            txtDepartement.setSelectedItem(note.getModule().getUe().getFiliere().getDepartement());
            txtFiliere.setSelectedItem(note.getModule().getUe().getFiliere());
            txtSemestre.setSelectedItem(note.getModule().getUe().getSemestre());
            txtAnneeScolaire.setSelectedItem(note.getAnneeUniversitaire());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        txtDepartement.addActionListener(evt -> onDepartementSelected());
    }

    private void onDepartementSelected() {
        System.out.println("onDepartementSelected appelé");
        txtFiliere.removeAllItems();

        ModelDepartement selectedDepartement = (ModelDepartement) txtDepartement.getSelectedItem();
        if (selectedDepartement != null) {
            try {
                ServiceModule serviceModule = new ServiceModule();
                List<ModelFiliere> filieres = serviceModule.getServiceFiliere().getFilieresByDepartementId(selectedDepartement.getDepartementId());

                // Utiliser un Set pour éliminer les doublons
                Set<ModelFiliere> uniqueFilieres = new HashSet<>(filieres);

                for (ModelFiliere filiere : uniqueFilieres) {
                    System.out.println("Ajout de la filière : " + filiere.getName());
                    txtFiliere.addItem(filiere);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNomEtudiant = new javax.swing.JTextField();
        txtNomModule = new javax.swing.JTextField();
        txtFiliere = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtCodeModule = new javax.swing.JTextField();
        txtDepartement = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtCodeUE = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNomUE = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSemestre = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtNote = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNomEnseignant = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtAnneeScolaire = new javax.swing.JComboBox<>();

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Nom Etudiant");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Nom Module");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Filiere");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Departement");

        txtFiliere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiliereActionPerformed(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Code Module");

        txtDepartement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartementActionPerformed(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Code UE");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Nom UE");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Semestre");

        txtSemestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSemestreActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Note");

        txtNote.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
            new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.##"))
        ));
        txtNote.setValue(0); // Définir une valeur par défaut

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Nom Enseignant");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Année Scolaire");

        txtAnneeScolaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnneeScolaireActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomEnseignant))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomUE)
                            .addComponent(txtNomModule)
                            .addComponent(txtCodeModule)
                            .addComponent(txtNomEtudiant)
                            .addComponent(txtCodeUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtNote))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAnneeScolaire, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSemestre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFiliere, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDepartement, 0, 199, Short.MAX_VALUE))))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomEnseignant, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodeModule, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomModule, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodeUE, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomUE, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDepartement, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiliere, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnneeScolaire, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtNote))
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtFiliereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiliereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiliereActionPerformed

    private void txtDepartementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartementActionPerformed

    private void txtSemestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSemestreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSemestreActionPerformed

    private void txtAnneeScolaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnneeScolaireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnneeScolaireActionPerformed

    public ModelNote getData() {
        try {
            // Récupérer les données saisies
            String nomEtudiant = txtNomEtudiant.getText().trim();
            String codeModule = txtCodeModule.getText().trim();
            String nomModule = txtNomModule.getText().trim();
            String codeUE = txtCodeUE.getText().trim();
            String nomUE = txtNomUE.getText().trim();
            String nomEnseignant = txtNomEnseignant.getText().trim();
            ModelAnneeUniversitaire anneeUniversitaire = (ModelAnneeUniversitaire) txtAnneeScolaire.getSelectedItem();

            ModelDepartement departement = (ModelDepartement) txtDepartement.getSelectedItem();
            ModelFiliere filiere = (ModelFiliere) txtFiliere.getSelectedItem();
            ModelSemestre semestre = (ModelSemestre) txtSemestre.getSelectedItem();

            // Vérifier que tous les champs obligatoires sont remplis
            if (nomEtudiant.isEmpty() || codeModule.isEmpty() || nomModule.isEmpty() || codeUE.isEmpty() || nomUE.isEmpty() || nomEnseignant.isEmpty() || departement == null || filiere == null || semestre == null || anneeUniversitaire == null) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez remplir tous les champs obligatoires.");
                return null;
            }

            // Vérifier que la note est valide
            if (txtNote.getValue() == null) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez entrer une note valide.");
                return null;
            }

            try {
                txtNote.commitEdit();
            } catch (ParseException e) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez entrer une note valide.");
                return null;
            }

            double noteValue;
            try {
                noteValue = Double.parseDouble(txtNote.getValue().toString());
                System.out.println("Valeur de la note saisie : " + noteValue);
                if (noteValue < 0 || noteValue > 20) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, "La note doit être comprise entre 0 et 20.");
                    return null;
                }
            } catch (NumberFormatException e) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez entrer une note valide.");
                return null;
            }

            // Créer l'UE
            ModelUE ue = new ModelUE();
            ue.setCode(codeUE);
            ue.setName(nomUE);
            ue.setSemestre(semestre);
            ue.setFiliere(filiere);

            // Créer le module
            ModelModule module = new ModelModule();
            module.setCode(codeModule);
            module.setName(nomModule);
            module.setUe(ue);

            // Récupérer l'étudiant par son nom
            ServiceEtudiant serviceEtudiant = new ServiceEtudiant();
            ModelEtudiant etudiant = serviceEtudiant.getEtudiantByName(nomEtudiant);
            if (etudiant == null) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "L'étudiant " + nomEtudiant + " n'existe pas. Veuillez d'abord créer l'étudiant.");
                return null;
            }

            // Récupérer l'enseignant par son nom
            ServiceEnseignant serviceEnseignant = new ServiceEnseignant();
            ModelEnseignant enseignant = serviceEnseignant.getEnseignantByName(nomEnseignant);
            if (enseignant == null) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "L'enseignant " + nomEnseignant + " n'existe pas. Veuillez d'abord créer l'enseignant.");
                return null;
            }
            module.setEnseignant(enseignant);

            // Créer l'objet ModelNote
            ModelNote note = new ModelNote();
            note.setEtudiant(etudiant);
            note.setModule(module);
            note.setNote(noteValue);
            note.setAnneeUniversitaire(anneeUniversitaire);

            // Retourner l'objet ModelNote
            return note;
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Une erreur s'est produite lors de la récupération des données.");
            return null;
        }
    }

    public String getNomEtudiant() {
        return txtNomEtudiant.getText();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<Object> txtAnneeScolaire;
    private javax.swing.JTextField txtCodeModule;
    private javax.swing.JTextField txtCodeUE;
    private javax.swing.JComboBox<Object> txtDepartement;
    private javax.swing.JComboBox<Object> txtFiliere;
    private javax.swing.JTextField txtNomEnseignant;
    private javax.swing.JTextField txtNomEtudiant;
    private javax.swing.JTextField txtNomModule;
    private javax.swing.JTextField txtNomUE;
    private javax.swing.JFormattedTextField txtNote;
    private javax.swing.JComboBox<Object> txtSemestre;
    // End of variables declaration//GEN-END:variables
}
