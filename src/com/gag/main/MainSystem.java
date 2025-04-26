package com.gag.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.gag.component.Menu;
import com.gag.event.EventMenuSelected;
import com.gag.form.Accueil;
import com.gag.form.DepartementFiliere;
import com.gag.form.Enseignants;
import com.gag.form.Etudiants;
import com.gag.form.Message;
import com.gag.form.SaisirNotes;
import com.gag.form.User;
import com.gag.form.Setting;
import com.gag.form.UeModule;
import com.gag.model.ModelMenu;
import com.gag.model.ModelUser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import raven.popup.GlassPanePopup;
import raven.toast.Notifications;


public class MainSystem extends javax.swing.JFrame {

    private final ModelUser user;
    private Menu menu;
    private JPanel mainSystem = new JPanel();
    private MigLayout layout;
    private Animator animator;
    private boolean menuShow;
    //private ServiceEnseignant serviceEnseignant = new ServiceEnseignant();
    
    public MainSystem(ModelUser user) {
        this.user = user;
        initComponents();
        init();
    }
    
    private void init() {
        GlassPanePopup.install(this);
        Notifications.getInstance().setJFrame(this);
        layout = new MigLayout("fill", "0[]10[]5", "0[fill]0");
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
                    showForm(new Accueil(user));
                } else if (index == 1) {
                    showForm(new Message());
                } else if (user.isAdmin()) {
                    // Gestion des menus spécifiques à l'admin
                    int adminIndex = index - 2; // Décalage pour les menus admin
                    switch (adminIndex) {
                        case 0 -> showForm(new User());
                        case 1 -> showForm(new Etudiants());
                        case 2 -> showForm(new Enseignants());
                        case 3 -> showForm(new DepartementFiliere());
                        case 4 -> showForm(new UeModule());
                        case 5 -> showForm(new SaisirNotes());
                        case 6 -> showForm(new Setting());
                        default -> System.out.println("Index non géré pour admin : " + index);
                    }
                } else if (user.isEnseignant()) {
                    // Gestion des menus spécifiques à l'enseignant
                    int enseignantIndex = index - 2; // Décalage pour les menus enseignant
                    switch (enseignantIndex) {
                        case 0 -> showForm(new UeModule());
                        case 1 -> showForm(new SaisirNotes());
                        default -> System.out.println("Index non géré pour enseignant : " + index);
                    }
                } else if (user.isEtudiant()) {
                    // Gestion des menus spécifiques à l'étudiant
                    int etudiantIndex = index - 2; // Décalage pour les menus étudiant
                    switch (etudiantIndex) {
                        case 0 -> showForm(new UeModule());
                        default -> System.out.println("Index non géré pour étudiant : " + index);
                    }
                } else {
                    System.out.println("Index non géré pour utilisateur simple : " + index);
                }
            }
        });

        // Ajout des menus dynamiques
        menu.addMenu(new ModelMenu("Accueil", new ImageIcon(getClass().getResource("/com/gag/icon/userS.png"))));
        menu.addMenu(new ModelMenu("Message", new ImageIcon(getClass().getResource("/com/gag/icon/message.png"))));

        // Ajout des menus spécifiques à l'administrateur (à la fin)
        if (user.isAdmin()) {
            menu.addMenu(new ModelMenu("Users", new ImageIcon(getClass().getResource("/com/gag/icon/userS.png"))));
            menu.addMenu(new ModelMenu("Etudiants", new ImageIcon(getClass().getResource("/com/gag/icon/report.png"))));
            menu.addMenu(new ModelMenu("Enseignants", new ImageIcon(getClass().getResource("/com/gag/icon/report.png"))));
            menu.addMenu(new ModelMenu("Département / Filière", new ImageIcon(getClass().getResource("/com/gag/icon/report.png"))));
            menu.addMenu(new ModelMenu("UE / Module", new ImageIcon(getClass().getResource("/com/gag/icon/report.png"))));
            menu.addMenu(new ModelMenu("Saisir Notes", new ImageIcon(getClass().getResource("/com/gag/icon/report.png"))));
        } else if (user.isEnseignant()) {
            // Ajout des menus spécifiques à l'enseignant
            menu.addMenu(new ModelMenu("UE / Module", new ImageIcon(getClass().getResource("/com/gag/icon/report.png"))));
            menu.addMenu(new ModelMenu("Saisir Notes", new ImageIcon(getClass().getResource("/com/gag/icon/report.png"))));
        } else if (user.isEtudiant()) {
            // Ajout des menus spécifiques à l'étudiant
            menu.addMenu(new ModelMenu("UE / Module", new ImageIcon(getClass().getResource("/com/gag/icon/report.png"))));
        }



        body.add(menu, "w 50!");
        body.add(mainSystem, "w 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menuShow) {
                    // Réduction du menu
                    width = 50 + (150 * (1f - fraction));
                    menu.setAlpha(1f - fraction);
                } else {
                    // Élargissement du menu
                    width = 50 + (170 * fraction);
                    menu.setAlpha(fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!");
                body.revalidate();
            }

            @Override
            public void end() {
                menuShow = !menuShow; // Inverse l'état du menu (ouvert/fermé)
            }
        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        showForm(new Accueil(user));
    }

    private void showForm(Component com) {
        mainSystem.removeAll();
        mainSystem.add(com, BorderLayout.CENTER);
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
            .addGap(0, 1200, Short.MAX_VALUE)
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
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("com.gag.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        
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
