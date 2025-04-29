package com.gag.component;

import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.gag.swing.ButtonOutLine;
import java.text.DecimalFormat;
import net.miginfocom.swing.MigLayout;

public class PanelCover extends javax.swing.JPanel {

    private final DecimalFormat df = new DecimalFormat("##0.###");
    private ActionListener event;
    private MigLayout layout;
    private JLabel title;
    private JLabel description;
    private JLabel subDescription;
    private ButtonOutLine button;
    private boolean isLogin;
    
    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        init();
    }

    private void init() {
        title = new JLabel("GaG-Etudiant");
        title.setFont(new Font("sansserif", 1, 30));
        title.setForeground(new Color(245, 245, 245));
        add(title);
        description = new JLabel("Application de gestion des Etudiants");
        description.setForeground(new Color(245, 245, 245));
        add(description);
        subDescription = new JLabel("Entrée vos information personnel pour vous connecté");
        subDescription.setForeground(new Color(245, 245, 245));
        add(subDescription);
        button = new ButtonOutLine();
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(255, 255, 255));
        button.setText("SIGN IN");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            event.actionPerformed(ae);
            }
        });
        add(button, "w 60%, h 40, gapleft 10"); // Ajoute un espacement de 20px à gauche
    }
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
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra=new GradientPaint(0, 0, new Color(35, 166, 97), 0, getHeight(), new Color(22, 116, 66));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }
    public void addEvent(ActionListener event){
        this.event = event;
    }

    public void registerLeft(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(subDescription, "pad 0 -" + v + "% 0 0");
    }
    
    public void registerRight(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(subDescription, "pad 0 -" + v + "% 0 0");
    }
    
    public void loginLeft(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        login(true);
        layout.setComponentConstraints(title, "pad 0" + v + "% 0" + v + "%");
        layout.setComponentConstraints(description, "pad 0" + v + "% 0" + v + "%");
        layout.setComponentConstraints(subDescription, "pad 0" + v + "% 0" + v + "%");
    }
    
    public void loginRight(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        login(true);
        layout.setComponentConstraints(title, "pad 0" + v + "% 0" + v + "%");
        layout.setComponentConstraints(description, "pad 0" + v + "% 0" + v + "%");
        layout.setComponentConstraints(subDescription, "pad 0" + v + "% 0" + v + "%");
    }
    
    private void login(boolean login) {
        if (this.isLogin!=login) {
            if (login) {
                title.setText("GaG-Etudiant");
                description.setText("Page de Connection");
                subDescription.setText("Entrée vos Information lors de la création de compte");
                button.setText("SIGN UP");
            } else {
                title.setText("GaG-Etudiant");
                description.setText("Application de gestion des Etudiants");
                subDescription.setText("Entrée vos information personnel pour vous connecté");
                button.setText("SIGN IN");
            }
            this.isLogin = login;
        } else {
        
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
