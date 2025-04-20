package com.gag.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.gag.component.EtudiantRegister;
import com.gag.component.InscriptionForm;
import com.gag.component.Message;
import com.gag.model.ModelUser;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.UIManager;
import raven.toast.Notifications;

public class EtudiantsAndInscriptions extends javax.swing.JFrame {

    private JPanel mainPanel = new JPanel();
    private EtudiantRegister etudiantRegister;
    private InscriptionForm inscriptionForm;

    public EtudiantsAndInscriptions() {
        initComponents();
        init();
    }
    
    private void init() {
        Notifications.getInstance().setJFrame(this);
        mainPanel.setOpaque(false);

        etudiantRegister = new EtudiantRegister();
        inscriptionForm = new InscriptionForm();
    }

    /*private void etudiantRegister() {
        ModelUser user = loginAndRegister.getUser();
        try {
            if (user.getUserName() == null || user.getUserName().trim().isEmpty() ||
                user.getEmail() == null || user.getEmail().trim().isEmpty() ||
                user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Please fill in all fields");
            } else if (!isValidEmail(user.getEmail())) {
                showMessage(Message.MessageType.ERROR, "Invalid email format");
            } else if (service.checkDuplicateUser(user.getUserName())) {
                showMessage(Message.MessageType.ERROR, "User name already exists");
            } else if (service.checkDuplicateEmail(user.getEmail())) {
                showMessage(Message.MessageType.ERROR, "Email already exists");
            } else {
                service.insertUser(user);
                //showMessage(Message.MessageType.SUCCESS, "User registered successfully!");
                this.dispose();
                MainSystem.main(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Affiche l'erreur dans la console
            showMessage(Message.MessageType.ERROR, "Error Register: " + e.getMessage());
        }
    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

     public static void main() {
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
    // End of variables declaration//GEN-END:variables
}
