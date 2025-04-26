package com.gag.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.gag.component.EtudiantRegister;
import com.gag.component.InscriptionForm;
import com.gag.component.Message;
import com.gag.model.ModelUser;
import com.gag.service.ServiceEtudiant;
import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;

import java.awt.Font;
import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.UIManager;

public class EtudiantsAndInscriptions extends javax.swing.JFrame {

    private ServiceEtudiant serviceEtudiant = new ServiceEtudiant();
    private JPanel mainPanel;

    public EtudiantsAndInscriptions() {
        initComponents();
        init();
    }

    private void init() {
        Notifications.getInstance().setJFrame(this);

        // Initialisation de MigLayout pour centrer le contenu
        bg.setLayout(new MigLayout("fill, insets 0", "[center]", "[center]"));

        // Ajouter le formulaire EtudiantRegister
        EtudiantRegister etudiantRegister = new EtudiantRegister();
        etudiantRegister.loadData(serviceEtudiant);

        // Ajouter le formulaire au panneau principal
        bg.add(etudiantRegister, "align center, width 50%, height 80%"); // Centrer et ajuster la taille

        // Configuration de la fenêtre
        setTitle("Gestion des Étudiants et Inscriptions");
        setSize(1000, 600); // Taille de la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 907, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("com.gag.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EtudiantsAndInscriptions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
