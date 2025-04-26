package com.gag.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.gag.component.CreateModuleUE;
import com.gag.table.CheckBoxTableHeaderRenderer;
import com.gag.table.TableHeaderAlignment;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import java.util.List;
import com.gag.service.ServiceModule;
import com.gag.model.ModelModule;
import raven.toast.Notifications;
import javax.swing.JLabel;
import java.util.ArrayList;
import com.gag.model.ModelDepartement;
import com.gag.model.ModelFiliere;
import com.gag.model.ModelSemestre;
import com.gag.model.ModelUE;
import com.gag.model.ModelEnseignant;

public class UeModule extends javax.swing.JPanel {

    ServiceModule serviceModule = new ServiceModule();

    public UeModule() {
        initComponents();
        setOpaque(false);
        init();
        loadData();
    }
    
    private void init() {
        //GlassPanePopup.install(this);
        //Notifications.getInstance().setJFrame(this);
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
    
    private void loadData() {
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

            // Parcourir les modules et ajouter les données au tableau
            for (ModelModule module : modules) {
                model.addRow(new Object[]{
                    false, // Checkbox non cochée par défaut
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        ueModuleTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        txtSearch = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        cmdEdit = new com.gag.swing.ButtonAction();
        cmdNew = new com.gag.swing.ButtonAction();
        cmdDelete = new com.gag.swing.ButtonAction();

        setBackground(new java.awt.Color(240, 240, 240));

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scroll.setPreferredSize(new java.awt.Dimension(1500, 400));

        ueModuleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "Nom de l'Enseignant", "Code du Module", "Nom du Module", "Code de Ue", "Nom de Ue", "Filiere", "Departements", "Séméstre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
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
            ueModuleTable.getColumnModel().getColumn(1).setMaxWidth(250);
            ueModuleTable.getColumnModel().getColumn(2).setMaxWidth(200);
            ueModuleTable.getColumnModel().getColumn(3).setMaxWidth(400);
            ueModuleTable.getColumnModel().getColumn(4).setMaxWidth(100);
            ueModuleTable.getColumnModel().getColumn(5).setMaxWidth(300);
            ueModuleTable.getColumnModel().getColumn(6).setMaxWidth(200);
            ueModuleTable.getColumnModel().getColumn(7).setMaxWidth(200);
            ueModuleTable.getColumnModel().getColumn(8).setMaxWidth(100);
        }

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        lbTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTitle.setText("Liste des Ues et des Modules");

        cmdEdit.setText("Edit");
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        cmdNew.setText("New");
        cmdNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNewActionPerformed(evt);
            }
        });

        cmdDelete.setText("Delete");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jSeparator1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTitle)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 477, Short.MAX_VALUE)
                                .addComponent(cmdNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        searchData(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchActionPerformed

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {
        List<ModelModule> list = getSelectedData(); // Récupérer les modules sélectionnés
        if (!list.isEmpty()) {
            if (list.size() == 1) {
                ModelModule module = list.get(0); // Récupérer le module sélectionné
                CreateModuleUE createModuleUE = new CreateModuleUE();
                createModuleUE.loadData(serviceModule, module); // Charger les données du module dans le formulaire

                DefaultOption option = new DefaultOption() {
                    @Override
                    public boolean closeWhenClickOutside() {
                        return true;
                    }
                };

                String actions[] = new String[]{"Cancel", "Update"};
                GlassPanePopup.showPopup(new SimplePopupBorder(createModuleUE, "Modifier Module [" + module.getName() + "]", actions, (pc, i) -> {
                    if (i == 1) { // Si l'utilisateur clique sur "Update"
                        try {
                            ModelModule dataEdit = createModuleUE.getData(); // Récupérer les données mises à jour
                            if (dataEdit != null) {
                                dataEdit.setModuleId(module.getModuleId()); // Conserver l'ID du module
                                serviceModule.editModule(dataEdit); // Mettre à jour le module dans la base de données
                                pc.closePopup();
                                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Module modifié avec succès.");
                                loadData(); // Recharger les données dans le tableau
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la modification du module.");
                        }
                    } else {
                        pc.closePopup(); // Fermer le popup si "Cancel" est cliqué
                    }
                }), option);
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner un seul module.");
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner un module à modifier.");
        }
    }

    private void cmdNewActionPerformed(java.awt.event.ActionEvent evt) {
        CreateModuleUE createModuleUE = new CreateModuleUE();
        createModuleUE.loadData(serviceModule);
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Cancel", "Save"};
        GlassPanePopup.showPopup(new SimplePopupBorder(createModuleUE, "Create Module UE", actions, (pc, i) -> {
            if (i == 1) {
                // Vérifier si l'enseignant existe
                String nomEnseignant = createModuleUE.getNomEnseignant();
                try {
                    // Vérifier si l'enseignant existe dans la base de données
                    boolean enseignantExiste = serviceModule.verifierEnseignantExiste(nomEnseignant);
                    
                    if (!enseignantExiste) {
                        Notifications.getInstance().show(Notifications.Type.WARNING, 
                            "L'enseignant " + nomEnseignant + " n'existe pas. Veuillez d'abord créer l'enseignant.");
                        return;
                    }
                    
                    // Si l'enseignant existe, procéder à l'enregistrement
                    ModelModule data = createModuleUE.getData();
                    if (data != null) {
                        serviceModule.insertModule(data);
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, 
                            "Le module a été créé avec succès.");
                        loadData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, 
                        "Erreur lors de la création du module.");
                }
            } else {
                pc.closePopup();
            }
        }), option);
    }

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        List<ModelModule> list = getSelectedData(); // Récupérer les modules sélectionnés
        if (!list.isEmpty()) {
            DefaultOption option = new DefaultOption() {
                @Override
                public boolean closeWhenClickOutside() {
                    return true;
                }
            };

            String actions[] = new String[]{"Cancel", "Delete"};
            JLabel label = new JLabel("Êtes-vous sûr de vouloir supprimer " + list.size() + " module(s) et leur(s) UE(s) associée(s)?");
            label.setBorder(new javax.swing.border.EmptyBorder(0, 25, 0, 25));

            GlassPanePopup.showPopup(new SimplePopupBorder(label, "Confirmer la suppression", actions, (pc, i) -> {
                if (i == 1) { // Si l'utilisateur clique sur "Delete"
                    try {
                        for (ModelModule module : list) {
                            serviceModule.deleteModule(module.getModuleId()); // Supprimer le module et son UE associée
                        }
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Module(s) et UE(s) supprimé(s) avec succès.");
                        loadData(); // Recharger les données dans le tableau
                    } catch (Exception e) {
                        e.printStackTrace();
                        Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la suppression des modules et UEs.");
                    }
                } else {
                    pc.closePopup(); // Fermer le popup si "Cancel" est cliqué
                }
            }), option);
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner un ou plusieurs modules à supprimer.");
        }
    }

    private List<ModelModule> getSelectedData() {
        List<ModelModule> list = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) ueModuleTable.getModel();
        int rows = model.getRowCount();
        
        try {
            // Récupérer tous les modules de la base de données
            List<ModelModule> allModules = serviceModule.getAllModules();
            
            for (int i = 0; i < rows; i++) {
                boolean selected = (boolean) model.getValueAt(i, 0);
                if (selected) {
                    // Récupérer les données du tableau
                    String codeModule = (String) model.getValueAt(i, 2);
                    String nomModule = (String) model.getValueAt(i, 3);
                    
                    // Trouver le module correspondant dans la liste des modules
                    for (ModelModule module : allModules) {
                        if (module.getCode().equals(codeModule) && module.getName().equals(nomModule)) {
                            list.add(module);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gag.swing.ButtonAction cmdDelete;
    private com.gag.swing.ButtonAction cmdEdit;
    private com.gag.swing.ButtonAction cmdNew;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTable ueModuleTable;
    // End of variables declaration//GEN-END:variables
}
