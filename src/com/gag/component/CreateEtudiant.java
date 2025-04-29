package com.gag.component;

import com.gag.model.ModelAnneeUniversitaire;
import com.gag.model.ModelEtudiant;
import com.gag.model.ModelFiliere;
import com.gag.service.ServiceEtudiant;
import java.sql.Date;

public class CreateEtudiant extends javax.swing.JPanel {

    public CreateEtudiant() {
        initComponents();
        datePicker = new raven.datetime.component.date.DatePicker(); // Initialisation
        datePicker.setCloseAfterSelected(true);
        datePicker.setEditor(txtDate);
    }

    public void loadData(ServiceEtudiant serviceEtudiant, ModelEtudiant data) {
        try {
            // Charger les années universitaires
            txtAnneeScolaire.removeAllItems(); // Effacer les anciens éléments
            for (ModelAnneeUniversitaire pos : serviceEtudiant.getServiceAnneeUniversitaire().getAllAnneesUniversitaires()) {
                txtAnneeScolaire.addItem(pos);
                if (data != null && data.getAnneeUniversitaire() != null && pos.getAnneeUniversitaireId() == data.getAnneeUniversitaire().getAnneeUniversitaireId()) {
                    txtAnneeScolaire.setSelectedItem(pos); // Sélectionner l'année universitaire correspondante
                }
            }

            // Charger les filières
            txtFiliere.removeAllItems(); // Effacer les anciens éléments
            for (ModelFiliere pos : serviceEtudiant.getServiceFiliere().getAllFilieres()) {
                txtFiliere.addItem(pos);
                if (data != null && data.getFiliere() != null && pos.getFiliereId() == data.getFiliere().getFiliereId()) {
                    txtFiliere.setSelectedItem(pos); // Sélectionner la filière correspondante
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Charger les autres champs si un étudiant est passé en paramètre
        if (data != null) {
            txtMatricule.setText(data.getMatricule());
            txtName.setText(data.getName());
            txtUserName.setText(data.getUserName());
            txtEmail.setText(data.getEmail());
            txtPhone.setText(data.getTelephone());
            datePicker.setSelectedDate(data.getDateNaissance().toLocalDate());
            if (data.getSexe() != null) {
                txtSexe.setSelectedItem(data.getSexe().toUpperCase().trim());
            }
        }
    }

    public ModelEtudiant getData() {
        try {
            String matricule = txtMatricule.getText().trim();
            String name = txtName.getText().trim();
            String userName = txtUserName.getText().trim();
            String email = txtEmail.getText().trim();
            String telephone = txtPhone.getText().trim();
            Date dateNaissance = datePicker.isDateSelected() ? Date.valueOf(datePicker.getSelectedDate()) : null;
            String sexe = (String) txtSexe.getSelectedItem();
            ModelAnneeUniversitaire anneeUniversitaire = (ModelAnneeUniversitaire) txtAnneeScolaire.getSelectedItem();
            ModelFiliere filiere = (ModelFiliere) txtFiliere.getSelectedItem();

            // Vérifiez que tous les champs sont remplis
            if (matricule.isEmpty() || name.isEmpty() || userName.isEmpty() || email.isEmpty() || telephone.isEmpty() || dateNaissance == null || filiere == null || anneeUniversitaire == null) {
                throw new IllegalArgumentException("Tous les champs doivent être remplis.");
            }

            return new ModelEtudiant(0, matricule, name, userName, email, telephone, new java.sql.Date(dateNaissance.getTime()), sexe, anneeUniversitaire, filiere);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setData(ModelEtudiant etudiant) {
        txtMatricule.setText(etudiant.getMatricule());
        txtName.setText(etudiant.getName());
        txtUserName.setText(etudiant.getUserName());
        txtEmail.setText(etudiant.getEmail());
        txtPhone.setText(etudiant.getTelephone());
        datePicker.setSelectedDate(etudiant.getDateNaissance().toLocalDate());
        if (etudiant.getSexe() != null) {
            txtSexe.setSelectedItem(etudiant.getSexe().toUpperCase().trim());
        }
        // Sélectionner l'année universitaire par ID
        for (int i = 0; i < txtAnneeScolaire.getItemCount(); i++) {
            ModelAnneeUniversitaire annee = (ModelAnneeUniversitaire) txtAnneeScolaire.getItemAt(i);
            if (annee.getAnneeUniversitaireId() == etudiant.getAnneeUniversitaire().getAnneeUniversitaireId()) {
                txtAnneeScolaire.setSelectedIndex(i);
                break;
            }
        }
        // Sélectionner la filière par ID
        for (int i = 0; i < txtFiliere.getItemCount(); i++) {
            ModelFiliere filiere = (ModelFiliere) txtFiliere.getItemAt(i);
            if (filiere.getFiliereId() == etudiant.getFiliere().getFiliereId()) {
                txtFiliere.setSelectedIndex(i);
                break;
            }
        }
    }

    public ModelAnneeUniversitaire getSelectedAnneeUniversitaire() {
        return (ModelAnneeUniversitaire) txtAnneeScolaire.getSelectedItem(); // Récupérer l'année universitaire sélectionnée
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datePicker = new raven.datetime.component.date.DatePicker();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtMatricule = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtDate = new javax.swing.JFormattedTextField();
        txtSexe = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtFiliere = new javax.swing.JComboBox<>();
        txtAnneeScolaire = new javax.swing.JComboBox<>();

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Nom");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Matricule");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Email Address");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Sexe");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Téléphone");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Date Naissance");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Annee Scolaire");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Filiere");

        txtSexe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        txtSexe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSexeActionPerformed(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Prénom");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName)
                    .addComponent(txtMatricule, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(txtDate)
                    .addComponent(txtPhone)
                    .addComponent(txtEmail)
                    .addComponent(txtSexe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUserName)
                    .addComponent(txtFiliere, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAnneeScolaire, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatricule, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSexe, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtEmail))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtPhone))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtDate))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnneeScolaire, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiliere, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSexeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSexeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.datetime.component.date.DatePicker datePicker;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<Object> txtAnneeScolaire;
    private javax.swing.JFormattedTextField txtDate;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JComboBox<Object> txtFiliere;
    private javax.swing.JTextField txtMatricule;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JComboBox<String> txtSexe;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
