package com.gag.component;

import com.gag.model.ModelUser;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class Bottom extends javax.swing.JPanel {

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
    
    private float alpha;
    private final ModelUser user;
    
    public Bottom(ModelUser user) {
        this.user = user;
        initComponents();
        setOpaque(false);
        setBackground(new Color(65, 152, 216));
        lbUser.setText(user.getUserName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageAvatar1 = new com.gag.swing.ImageAvatar();
        lbUser = new javax.swing.JLabel();
        lbState = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gag/icon/profile.jpg"))); // NOI18N

        lbUser.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbUser.setForeground(new java.awt.Color(235, 235, 235));
        lbUser.setText("User");

        lbState.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lbState.setForeground(new java.awt.Color(235, 235, 235));
        lbState.setText("Administrator");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUser)
                    .addComponent(lbState))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbUser)
                        .addGap(3, 3, 3)
                        .addComponent(lbState))
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(getBackground());
        g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);
        super.paint(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gag.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel lbState;
    private javax.swing.JLabel lbUser;
    // End of variables declaration//GEN-END:variables
}
