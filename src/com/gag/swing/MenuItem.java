package com.gag.swing;

import com.gag.event.EventMenuSelected;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.SwingUtilities;


public class MenuItem extends javax.swing.JPanel {

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
    
    private final List<EventMenuSelected> events = new ArrayList<>();
    private int index;
    private boolean selected;
    private boolean mouseOver;
    public MenuItem(Icon icon, String name, int index) {
        initComponents();
        setOpaque(false);
        this.index = index;
        ibIcon.setIcon(icon);
        ibName.setText(name);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
            }
            
            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
            }
            
            @Override 
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (mouseOver) {
                        setSelected(true);
                        repaint();
                        runEvent();
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (selected) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(1, 122, 167));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setComposite(AlphaComposite.SrcOver);
            g2.setColor(new Color(245, 245, 245));
            g2.fillRect(0, 0, 2, getHeight());
        }
        super.paintComponent(grphcs);
    }
    
    private void runEvent() {
        for (EventMenuSelected event : events) {
            event.selected(index);
        }
    }

    public void addEvent(EventMenuSelected event) {
        events.add(event);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ibIcon = new javax.swing.JLabel();
        ibName = new javax.swing.JLabel();

        ibIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        ibName.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        ibName.setForeground(new java.awt.Color(250, 250, 250));
        ibName.setText("Menu Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(ibIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ibName)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ibIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ibName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ibIcon;
    private javax.swing.JLabel ibName;
    // End of variables declaration//GEN-END:variables
}
