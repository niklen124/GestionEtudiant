package com.gag.component;

import com.gag.model.ModelEnseignant;
import com.gag.model.ModelFiliere;
import com.gag.model.ModelDepartement;
import com.gag.service.ServiceEnseignant;
import raven.toast.Notifications;

public class CreateDepartementFiliere extends javax.swing.JPanel {

    public CreateDepartementFiliere() {
        initComponents();
    }

    public void loadData(ModelFiliere data) {
        if (data != null) {
            txtDepartementName.setText(data.getDepartement().getName()); // Charger le nom du département
            txtFiliereName.setText(data.getName()); // Charger le nom de la filière
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtDepartementName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFiliereName = new javax.swing.JTextField();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Nom du Departement");

        txtDepartementName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartementNameActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Nom de la Filiere");

        txtFiliereName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiliereNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDepartementName, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(txtFiliereName))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDepartementName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFiliereName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDepartementNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartementNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartementNameActionPerformed

    private void txtFiliereNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiliereNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiliereNameActionPerformed

    public ModelFiliere getData() {
        String departementName = txtDepartementName.getText().trim();
        String filiereName = txtFiliereName.getText().trim();

        // Validation des champs
        if (departementName.isEmpty() || filiereName.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Tous les champs sont obligatoires.");
            return null;
        }

        // Créer un objet ModelDepartement
        ModelDepartement departement = new ModelDepartement();
        departement.setName(departementName);

        // Créer un objet ModelFiliere
        return new ModelFiliere(0, filiereName, departement);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtDepartementName;
    private javax.swing.JTextField txtFiliereName;
    // End of variables declaration//GEN-END:variables
}
