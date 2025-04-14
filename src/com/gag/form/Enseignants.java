package com.gag.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.gag.component.CreateEnseignant;
import com.gag.model.ModelEnseignant;
import com.gag.service.ServiceEnseignant;
import com.gag.table.CheckBoxTableHeaderRenderer;
import com.gag.table.TableHeaderAlignment;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;


public class Enseignants extends javax.swing.JPanel {

    private ServiceEnseignant serviceEnseignant = new ServiceEnseignant();
    
    public Enseignants() {
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
        
        enseignantTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        
        enseignantTable.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:30;"
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
        
        enseignantTable.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(enseignantTable, 0));
        enseignantTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(enseignantTable));
        //   userTable.getColumnModel().getColumn(2).setCellRenderer(new ProfileTableRenderer(userTable));       
    }
    
    private void loadData() {
        try {
            DefaultTableModel model = (DefaultTableModel) enseignantTable.getModel();

            // Arrêter l'édition si le tableau est en mode édition
            if (enseignantTable.isEditing()) {
                enseignantTable.getCellEditor().stopCellEditing();
            }

            // Effacer les anciennes données
            model.setRowCount(0);

            // Récupérer les utilisateurs via le service
            ServiceEnseignant service = new ServiceEnseignant();
            List<ModelEnseignant> list = service.getAllEnseignants();
            for(ModelEnseignant d:list) {
                model.addRow(d.toTableRow(enseignantTable.getRowCount() + 1));
            }
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void searchData(String search) {
        try {
            DefaultTableModel model = (DefaultTableModel) enseignantTable.getModel();

            // Arrêter l'édition si le tableau est en mode édition
            if (enseignantTable.isEditing()) {
                enseignantTable.getCellEditor().stopCellEditing();
            }

            // Effacer les anciennes données
            model.setRowCount(0);

            // Récupérer les utilisateurs via le service
            ServiceEnseignant service = new ServiceEnseignant();
            List<ModelEnseignant> list = service.searchEnseignants(search);
            for(ModelEnseignant d:list) {
                model.addRow(d.toTableRow(enseignantTable.getRowCount() + 1));
            }
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        enseignantTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        txtSearch = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        cmdEdit = new com.gag.swing.ButtonAction();
        cmdNew = new com.gag.swing.ButtonAction();
        cmdDelete = new com.gag.swing.ButtonAction();

        setBackground(new java.awt.Color(240, 240, 240));

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scroll.setPreferredSize(new java.awt.Dimension(1000, 400));

        enseignantTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "#", "Nom", "Prenom", "Email", "Niveau", "Departement"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        enseignantTable.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(enseignantTable);
        if (enseignantTable.getColumnModel().getColumnCount() > 0) {
            enseignantTable.getColumnModel().getColumn(0).setMaxWidth(50);
            enseignantTable.getColumnModel().getColumn(1).setMaxWidth(40);
        }

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lbTitle.setText("Liste des Enseignants");

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
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 568, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
                                .addComponent(cmdNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        List<ModelEnseignant> list = getSelectedData(); // Récupérer les enseignants sélectionnés
        if (!list.isEmpty()) {
            if (list.size() == 1) {
                ModelEnseignant enseignant = list.get(0); // Récupérer l'enseignant sélectionné
                CreateEnseignant createEnseignant = new CreateEnseignant();
                createEnseignant.loadData(serviceEnseignant, enseignant); // Charger les données de l'enseignant dans le formulaire

                DefaultOption option = new DefaultOption() {
                    @Override
                    public boolean closeWhenClickOutside() {
                        return true;
                    }
                };

                String actions[] = new String[]{"Cancel", "Update"};
                GlassPanePopup.showPopup(new SimplePopupBorder(createEnseignant, "Edit Enseignant [" + enseignant.getName() + "]", actions, (pc, i) -> {
                    if (i == 1) { // Si l'utilisateur clique sur "Update"
                        try {
                            ModelEnseignant dataEdit = createEnseignant.getData(); // Récupérer les données mises à jour
                            if (dataEdit != null) {
                                dataEdit.setEnseignantId(enseignant.getEnseignantId()); // Conserver l'ID de l'enseignant
                                serviceEnseignant.editEnseignant(dataEdit); // Mettre à jour l'enseignant dans la base de données
                                pc.closePopup();
                                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Enseignant a été modifié avec succès.");
                                loadData(); // Recharger les données dans le tableau
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la modification de l'enseignant.");
                        }
                    } else {
                        pc.closePopup(); // Fermer le popup si "Cancel" est cliqué
                    }
                }), option);
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner un seul enseignant.");
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner un enseignant à modifier.");
        }
    }//GEN-LAST:event_cmdEditActionPerformed

    private List<ModelEnseignant> getSelectedData() {
        List<ModelEnseignant> list = new ArrayList<>();
        for (int i = 0; i < enseignantTable.getRowCount(); i++) {
            if ((boolean) enseignantTable.getValueAt(i, 0)) { // Vérifie si la checkbox est cochée
                ModelEnseignant data = (ModelEnseignant) enseignantTable.getValueAt(i, 2);
                list.add(data);
            }
        }
        return list;
    }
    
    private void cmdNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNewActionPerformed
        CreateEnseignant createEnseignant = new CreateEnseignant();
        createEnseignant.loadData(serviceEnseignant, null);
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Cancel", "Save"};
        GlassPanePopup.showPopup(new SimplePopupBorder(createEnseignant, "Create Enseignant", actions, (pc, i) -> {
            if (i == 1) {
                // save
                try {
                    ModelEnseignant data = createEnseignant.getData();
                    if (data != null) { 
                        serviceEnseignant.createEnseignant(data);
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Enseignant a été créé avec succès.");
                        loadData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la création de l'enseignant.");
                }
            } else {
                pc.closePopup();
            }
        }), option);
    }//GEN-LAST:event_cmdNewActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        List<ModelEnseignant> list = getSelectedData(); // Récupérer les enseignants sélectionnés
        if (!list.isEmpty()) {
            DefaultOption option = new DefaultOption() {
                @Override
                public boolean closeWhenClickOutside() {
                    return true;
                }
            };

            String actions[] = new String[]{"Cancel", "Delete"};
            JLabel label = new JLabel("Are you sure to delete " + list.size() + " enseignant(s)?");
            label.setBorder(new javax.swing.border.EmptyBorder(0, 25, 0, 25));

            GlassPanePopup.showPopup(new SimplePopupBorder(label, "Confirm Delete", actions, (pc, i) -> {
                if (i == 1) { // Si l'utilisateur clique sur "Delete"
                    try {
                        for (ModelEnseignant enseignant : list) {
                            serviceEnseignant.deleteEnseignant(enseignant.getEnseignantId()); // Supprimer l'enseignant
                        }
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Enseignant(s) supprimé(s) avec succès.");
                        loadData(); // Recharger les données dans le tableau
                    } catch (Exception e) {
                        e.printStackTrace();
                        Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la suppression des enseignants.");
                    }
                } else {
                    pc.closePopup(); // Fermer le popup si "Cancel" est cliqué
                }
            }), option);
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner un ou plusieurs enseignants à supprimer.");
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchData(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gag.swing.ButtonAction cmdDelete;
    private com.gag.swing.ButtonAction cmdEdit;
    private com.gag.swing.ButtonAction cmdNew;
    private javax.swing.JTable enseignantTable;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
