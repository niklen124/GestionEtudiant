package com.gag.component;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.gag.main.MainSystem;
import com.gag.table.CheckBoxTableHeaderRenderer;
import com.gag.table.TableHeaderAlignment;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import com.gag.service.ServiceModule;
import com.gag.model.ModelModule;
import raven.toast.Notifications;
import java.util.ArrayList;
import com.gag.service.ServiceInscription;
import com.gag.model.ModelInscription;
import com.gag.model.ModelEtudiant;
import com.gag.model.ModelUser;

public class InscriptionForm extends javax.swing.JPanel {

    private ModelEtudiant etudiant;
    private ModelUser user; // Ajout de l'utilisateur
    ServiceModule serviceModule = new ServiceModule();

    public InscriptionForm(ModelEtudiant etudiant, ModelUser user) {
        this.etudiant = etudiant; // Stocker l'étudiant
        this.user = user; // Stocker l'utilisateur
        initComponents();
        setOpaque(false);
        init();
        loadModulesByFiliere(etudiant.getFiliere().getFiliereId()); // Charger les modules de la filière
    }
    
    private void init() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        
        ueModuleTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        
        ueModuleTable.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:30;" // Augmente la hauteur des lignes
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");

        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:$Table.background;");
        
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5;");

        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("com/gag/icon/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background");
        
        ueModuleTable.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(ueModuleTable, 0));
        ueModuleTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(ueModuleTable));
    }
    
    

    private void searchData(String search) {
        try {
            DefaultTableModel model = (DefaultTableModel) ueModuleTable.getModel();

            // Arrêter l'édition si le tableau est en mode édition
            if (ueModuleTable.isEditing()) {
                ueModuleTable.getCellEditor().stopCellEditing();
            }

            // Effacer les anciennes données
            model.setRowCount(0);

            // Récupérer les modules via le service
            List<ModelModule> modules = serviceModule.getAllModules();
            
            // Filtrer les modules selon le critère de recherche
            for (ModelModule module : modules) {
                if (module.getName().toLowerCase().contains(search.toLowerCase()) ||
                    module.getCode().toLowerCase().contains(search.toLowerCase()) ||
                    module.getUe().getName().toLowerCase().contains(search.toLowerCase()) ||
                    module.getUe().getCode().toLowerCase().contains(search.toLowerCase()) ||
                    module.getEnseignant().getName().toLowerCase().contains(search.toLowerCase()) ||
                    module.getUe().getFiliere().getName().toLowerCase().contains(search.toLowerCase()) ||
                    module.getUe().getFiliere().getDepartement().getName().toLowerCase().contains(search.toLowerCase()) ||
                    module.getUe().getSemestre().getName().toLowerCase().contains(search.toLowerCase())) {
                    
                    model.addRow(new Object[]{
                        false, // Checkbox non cochée par défaut
                        module.getModuleId(), // ID du module (doit être un entier)
                        module.getEnseignant().getName(), // Nom de l'enseignant
                        module.getCode(), // Code du module
                        module.getName(), // Nom du module
                        module.getUe().getCode(), // Code de l'UE
                        module.getUe().getName(), // Nom de l'UE
                        module.getUe().getFiliere().getName(),
                        module.getUe().getFiliere().getDepartement().getName(), // Nom du département
                        module.getUe().getSemestre().getName() // Nom du semestre
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadModulesByFiliere(int filiereId) {
        try {
            DefaultTableModel model = (DefaultTableModel) ueModuleTable.getModel();

            // Arrêter l'édition si le tableau est en mode édition
            if (ueModuleTable.isEditing()) {
                ueModuleTable.getCellEditor().stopCellEditing();
            }

            // Effacer les anciennes données
            model.setRowCount(0);

            // Récupérer les modules via le service
            List<ModelModule> modules = serviceModule.getAllModulesByFiliere(filiereId);

            // Parcourir les modules et ajouter les données au tableau
            for (ModelModule module : modules) {
                model.addRow(new Object[]{
                    false, // Checkbox non cochée par défaut
                    module.getModuleId(), // ID du module (doit être un entier)
                    module.getEnseignant().getName(), // Nom de l'enseignant
                    module.getCode(), // Code du module
                    module.getName(), // Nom du module
                    module.getUe().getCode(), // Code de l'UE
                    module.getUe().getName(), // Nom de l'UE
                    module.getUe().getFiliere().getName(),
                    module.getUe().getFiliere().getDepartement().getName(), // Nom du département
                    module.getUe().getSemestre().getName() // Nom du semestre
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchToMainSystem() {
        // Récupérer le conteneur parent (le JFrame)
        java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (parentWindow != null) {
            parentWindow.dispose(); // Fermer la fenêtre actuelle
        }

        // Ouvrir MainSystem
        MainSystem mainSystem = new MainSystem(user);
        mainSystem.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        ueModuleTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        lbTitle = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        txtAdd = new com.gag.swing.ButtonAction();

        setBackground(new java.awt.Color(240, 240, 240));

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scroll.setPreferredSize(new java.awt.Dimension(1500, 400));

        ueModuleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "ID", "Nom de l'Enseignant", "Code du Module", "Nom du Module", "Code de Ue", "Nom de Ue", "Filiere", "Departements", "Séméstre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ueModuleTable.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(ueModuleTable);
        if (ueModuleTable.getColumnModel().getColumnCount() > 0) {
            ueModuleTable.getColumnModel().getColumn(0).setMaxWidth(50);
            ueModuleTable.getColumnModel().getColumn(1).setMinWidth(0);
            ueModuleTable.getColumnModel().getColumn(1).setPreferredWidth(0);
            ueModuleTable.getColumnModel().getColumn(1).setMaxWidth(0);
            ueModuleTable.getColumnModel().getColumn(2).setMaxWidth(250);
            ueModuleTable.getColumnModel().getColumn(3).setMaxWidth(200);
            ueModuleTable.getColumnModel().getColumn(4).setMaxWidth(400);
            ueModuleTable.getColumnModel().getColumn(5).setMaxWidth(100);
            ueModuleTable.getColumnModel().getColumn(6).setMaxWidth(300);
            ueModuleTable.getColumnModel().getColumn(7).setMaxWidth(200);
            ueModuleTable.getColumnModel().getColumn(8).setMaxWidth(200);
            ueModuleTable.getColumnModel().getColumn(9).setMaxWidth(100);
        }

        lbTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTitle.setText("Liste des Ues et des Modules");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        txtAdd.setText("Add");
        txtAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                            .addComponent(jSeparator1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitle)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddActionPerformed
        try {
            // Récupérer les modules sélectionnés
            DefaultTableModel model = (DefaultTableModel) ueModuleTable.getModel();
            int rowCount = model.getRowCount();
            List<ModelModule> selectedModules = new ArrayList<>();

            for (int i = 0; i < rowCount; i++) {
                Boolean isSelected = (Boolean) model.getValueAt(i, 0); // Vérifier si la case est cochée
                if (isSelected != null && isSelected) {
                    // Récupérer le module correspondant
                    int moduleId = Integer.parseInt(model.getValueAt(i, 1).toString()); // Convertir la valeur String en Integer
                    ModelModule module = serviceModule.getModuleById(moduleId); // Implémentez cette méthode dans ServiceModule
                    selectedModules.add(module);
                }
            }

            // Vérifier si au moins un module est sélectionné
            if (selectedModules.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner au moins un module.");
                return;
            }

            // Inscrire l'étudiant aux modules sélectionnés
            ServiceInscription serviceInscription = new ServiceInscription();
            for (ModelModule module : selectedModules) {
                ModelInscription inscription = new ModelInscription();
                inscription.setEtudiant(etudiant); // L'étudiant doit être passé à InscriptionForm
                inscription.setModule(module);
                inscription.setAnneeUniversitaire(etudiant.getAnneeUniversitaire());

                serviceInscription.inscrireEtudiant(inscription);
            }

            // Afficher une notification de succès
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Modules inscrits avec succès.");

            // Basculer vers MainSystem
            switchToMainSystem();

        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Une erreur est survenue : " + e.getMessage());
        }
    }//GEN-LAST:event_txtAddActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchData(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchKeyReleased

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JScrollPane scroll;
    private com.gag.swing.ButtonAction txtAdd;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTable ueModuleTable;
    // End of variables declaration//GEN-END:variables
}
