/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.gag.form;

import com.gag.model.ModelUser;
import javax.swing.SwingConstants;

/**
 * Classe représentant l'écran d'accueil avec les informations de l'utilisateur connecté.
 */
public class Accueil extends javax.swing.JPanel {

    private ModelUser utilisateur; // Utilisateur connecté

    /**
     * Crée un nouvel écran d'accueil.
     * @param utilisateur L'utilisateur connecté.
     */
    public Accueil(ModelUser utilisateur) {
        this.utilisateur = utilisateur;
        initComponents();
        afficherInformationsUtilisateur();
    }

    /**
     * Cette méthode est appelée depuis le constructeur pour initialiser les composants.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWelcome = new javax.swing.JLabel();
        lblNomUtilisateur = new javax.swing.JLabel();
        lblEmailUtilisateur = new javax.swing.JLabel();
        lblTypeUtilisateur = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lblWelcome.setFont(new java.awt.Font("Arial", 1, 24)); // Police et taille
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setText("Bienvenue dans l'application de gestion des étudiants");

        lblNomUtilisateur.setFont(new java.awt.Font("Arial", 0, 18)); // Police et taille
        lblNomUtilisateur.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomUtilisateur.setText("Nom de l'utilisateur : ");

        lblEmailUtilisateur.setFont(new java.awt.Font("Arial", 0, 18)); // Police et taille
        lblEmailUtilisateur.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmailUtilisateur.setText("Email de l'utilisateur : ");

        lblTypeUtilisateur.setFont(new java.awt.Font("Arial", 0, 18)); // Police et taille
        lblTypeUtilisateur.setHorizontalAlignment(SwingConstants.CENTER);
        lblTypeUtilisateur.setText("Type d'utilisateur : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(lblNomUtilisateur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEmailUtilisateur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTypeUtilisateur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblWelcome)
                .addGap(50, 50, 50)
                .addComponent(lblNomUtilisateur)
                .addGap(18, 18, 18)
                .addComponent(lblEmailUtilisateur)
                .addGap(18, 18, 18)
                .addComponent(lblTypeUtilisateur)
                .addContainerGap(150, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Affiche les informations de l'utilisateur connecté.
     */
    private void afficherInformationsUtilisateur() {
        if (utilisateur != null) {
            lblNomUtilisateur.setText("Nom de l'utilisateur : " + utilisateur.getUserName());
            lblEmailUtilisateur.setText("Email de l'utilisateur : " + utilisateur.getEmail());
            lblTypeUtilisateur.setText("Type d'utilisateur : " + utilisateur.getUserType());
        } else {
            lblNomUtilisateur.setText("Nom de l'utilisateur : Non disponible");
            lblEmailUtilisateur.setText("Email de l'utilisateur : Non disponible");
            lblTypeUtilisateur.setText("Type d'utilisateur : Non disponible");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JLabel lblNomUtilisateur;
    private javax.swing.JLabel lblEmailUtilisateur;
    private javax.swing.JLabel lblTypeUtilisateur;
    // End of variables declaration//GEN-END:variables
}
