package com.gag.main;

import com.gag.component.Menu;
import com.gag.event.EventMenuSelected;
import com.gag.form.Message;
import com.gag.form.Profile;
import com.gag.model.ModelMenu;
import com.gag.model.ModelUser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;


public class MainSystem extends javax.swing.JFrame {

    private final ModelUser user;
    private Menu menu;
    private JPanel mainSystem = new JPanel();
    private MigLayout layout;
    private Animator animator;
    private boolean menuShow;
    public MainSystem(ModelUser user) {
        this.user = user;
        initComponents();
        init();
    }
    
    private void init() {
        layout = new MigLayout("fill", "0[]10[]0", "0[fill]0");
        body.setLayout(layout);
        mainSystem.setOpaque(false);
        mainSystem.setLayout(new BorderLayout());
        
        menu = new Menu(user);
        
        menu.addEventLogout(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //System.out.println("Logout");
                dispose(); // Ferme la fenêtre actuelle
                new Main().setVisible(true); // Affiche l'écran de connexion
            }
        });
        menu.addEventMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //System.out.println("Menu Selected");
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
        menu.setEvent(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    showForm(new Profile());
                } else if (index == 1) {
                    showForm(new Message());
                }
            }
        });
        menu.addMenu(new ModelMenu("Profile", new ImageIcon(getClass().getResource("/com/gag/icon/userS.png"))));
        menu.addMenu(new ModelMenu("Message", new ImageIcon(getClass().getResource("/com/gag/icon/message.png"))));
        menu.addMenu(new ModelMenu("Report", new ImageIcon(getClass().getResource("/com/gag/icon/report.png"))));
        menu.addMenu(new ModelMenu("Setting", new ImageIcon(getClass().getResource("/com/gag/icon/setting.png"))));
        menu.addMenu(new ModelMenu("Key", new ImageIcon(getClass().getResource("/com/gag/icon/key.png"))));
        body.add(menu, "w 50!");
        body.add(mainSystem, "w 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menuShow) {
                    width = 50 + (150 * (1f - fraction));
                    menu.setAlpha(1f - fraction);
                } else {
                    width = 50 + (150 * fraction);
                    menu.setAlpha(fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!");
                body.revalidate();
            }

            @Override
            public void end() {
                menuShow = !menuShow;
            }
        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        showForm(new Profile());
    }

    private void showForm(Component com) {
        mainSystem.removeAll();
        mainSystem.add(com);
        mainSystem.repaint();
        mainSystem.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        body.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1071, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(ModelUser user) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainSystem(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    // End of variables declaration//GEN-END:variables

    
}
