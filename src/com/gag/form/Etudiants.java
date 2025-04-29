package com.gag.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.gag.component.CreateEtudiant;
import com.gag.model.ModelAnneeUniversitaire;
import com.gag.model.ModelEtudiant;
import com.gag.model.ModelFiliere;
import com.gag.service.ServiceEtudiant;
import com.gag.table.CheckBoxTableHeaderRenderer;
import com.gag.table.TableHeaderAlignment;
import java.util.ArrayList;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JLabel;

public class Etudiants extends javax.swing.JPanel {
    
    private ServiceEtudiant serviceEtudiant = new ServiceEtudiant();

    public Etudiants() {
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
        
        etudiantTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        
        etudiantTable.putClientProperty(FlatClientProperties.STYLE, ""
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
        
        etudiantTable.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(etudiantTable, 0));
        etudiantTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(etudiantTable));
    }
    
    private void loadData() {
        try {
            List<ModelEtudiant> etudiants = serviceEtudiant.getAllEtudiant();
            DefaultTableModel model = (DefaultTableModel) etudiantTable.getModel();
            model.setRowCount(0); // Réinitialiser le tableau

            for (ModelEtudiant etudiant : etudiants) {
                model.addRow(new Object[] {
                    false, // Checkbox
                    etudiant.getMatricule(),
                    etudiant.getName(),
                    etudiant.getUserName(),
                    etudiant.getEmail(),
                    etudiant.getTelephone(),
                    etudiant.getDateNaissance(),
                    etudiant.getAnneeUniversitaire().getLibelle(),
                    etudiant.getFiliere().getName(),
                    etudiant.getEtudiantId() // Ajouter l'ID dans la colonne cachée
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors du chargement des données.");
        }
    }

    private void searchData(String search) {
        try {
            DefaultTableModel model = (DefaultTableModel) etudiantTable.getModel();

            // Arrêter l'édition si le tableau est en mode édition
            if (etudiantTable.isEditing()) {
                etudiantTable.getCellEditor().stopCellEditing();
            }

            // Effacer les anciennes données
            model.setRowCount(0);

            // Récupérer les étudiants via le service
            List<ModelEtudiant> list = serviceEtudiant.searchEtudiant(search);
            for (ModelEtudiant etudiant : list) {
                model.addRow(new Object[] {
                    false, // Checkbox
                    etudiant.getMatricule(),
                    etudiant.getName(),
                    etudiant.getUserName(),
                    etudiant.getEmail(),
                    etudiant.getTelephone(),
                    etudiant.getDateNaissance(),
                    etudiant.getAnneeUniversitaire().getLibelle(),
                    etudiant.getFiliere().getName()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la recherche des étudiants.");
        }
    }

    private List<ModelEtudiant> getSelectedData() {
        List<ModelEtudiant> selectedEtudiants = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) etudiantTable.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean isSelected = (Boolean) model.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                int etudiantId = (int) model.getValueAt(i, 9);
                try {
                    // Récupérer l'étudiant complet depuis la base de données
                    ModelEtudiant etudiant = serviceEtudiant.getEtudiantById(etudiantId);
                    if (etudiant != null) {
                        selectedEtudiants.add(etudiant);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return selectedEtudiants;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        etudiantTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        txtSearch = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        cmdEdit = new com.gag.swing.ButtonAction();
        cmdNew = new com.gag.swing.ButtonAction();
        cmdDelete = new com.gag.swing.ButtonAction();

        setBackground(new java.awt.Color(240, 240, 240));

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scroll.setPreferredSize(new java.awt.Dimension(1500, 400));

        etudiantTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "Matricule", "Nom", "Prenom", "Email", "Téléphone", "Date Naissance", "Année Scolaire", "Filliere", "ID"
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
        etudiantTable.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(etudiantTable);
        if (etudiantTable.getColumnModel().getColumnCount() > 0) {
            etudiantTable.getColumnModel().getColumn(0).setMaxWidth(50);
            etudiantTable.getColumnModel().getColumn(1).setMaxWidth(100);
            etudiantTable.getColumnModel().getColumn(2).setMaxWidth(150);
            etudiantTable.getColumnModel().getColumn(3).setMaxWidth(450);
            etudiantTable.getColumnModel().getColumn(4).setMaxWidth(400);
            etudiantTable.getColumnModel().getColumn(5).setMaxWidth(200);
            etudiantTable.getColumnModel().getColumn(6).setPreferredWidth(80);
            etudiantTable.getColumnModel().getColumn(6).setMaxWidth(200);
            etudiantTable.getColumnModel().getColumn(7).setPreferredWidth(80);
            etudiantTable.getColumnModel().getColumn(7).setMaxWidth(150);
            etudiantTable.getColumnModel().getColumn(8).setPreferredWidth(100);
            etudiantTable.getColumnModel().getColumn(8).setMaxWidth(150);
            etudiantTable.getColumnModel().getColumn(9).setMinWidth(0);
            etudiantTable.getColumnModel().getColumn(9).setPreferredWidth(0);
            etudiantTable.getColumnModel().getColumn(9).setMaxWidth(0);
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

        lbTitle.setText("Etudiant List");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitle)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmdNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
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
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        List<ModelEtudiant> list = getSelectedData(); // Récupérer les étudiants sélectionnés
        if (!list.isEmpty()) {
            if (list.size() == 1) {
                ModelEtudiant etudiant = list.get(0); // Récupérer l'étudiant sélectionné
                CreateEtudiant createEtudiant = new CreateEtudiant();
                createEtudiant.loadData(serviceEtudiant, etudiant); // Charger les données nécessaires (filières, années scolaires, etc.)
                createEtudiant.setData(etudiant); // Charger les données de l'étudiant dans le formulaire

                DefaultOption option = new DefaultOption() {
                    @Override
                    public boolean closeWhenClickOutside() {
                        return true;
                    }
                };

                String actions[] = new String[]{"Cancel", "Update"};
                GlassPanePopup.showPopup(new SimplePopupBorder(createEtudiant, "Edit Étudiant [" + etudiant.getName() + "]", actions, (pc, i) -> {
                    if (i == 1) { // Si l'utilisateur clique sur "Update"
                        try {
                            ModelEtudiant dataEdit = createEtudiant.getData(); // Récupérer les données mises à jour
                            if (dataEdit != null) {
                                dataEdit.setEtudiantId(etudiant.getEtudiantId()); // Conserver l'ID de l'étudiant
                                serviceEtudiant.editEtudiant(dataEdit); // Mettre à jour l'étudiant dans la base de données
                                pc.closePopup();
                                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Étudiant a été modifié avec succès.");
                                loadData(); // Recharger les données dans le tableau
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la modification de l'étudiant.");
                        }
                    } else {
                        pc.closePopup(); // Fermer le popup si "Cancel" est cliqué
                    }
                }), option);
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner un seul étudiant.");
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner un étudiant à modifier.");
        }
    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNewActionPerformed
        CreateEtudiant createEtudiant = new CreateEtudiant();
        createEtudiant.loadData(serviceEtudiant, null); // Charger les données nécessaires (filières, années scolaires, etc.)
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Cancel", "Save"};
        GlassPanePopup.showPopup(new SimplePopupBorder(createEtudiant, "Create Etudiant", actions, (pc, i) -> {
            if (i == 1) {
                // Sauvegarder les données
                try {
                    ModelEtudiant data = createEtudiant.getData(); // Récupérer les données saisies
                    if (data != null) {
                        // Créer un nouvel étudiant
                        int etudiantId = serviceEtudiant.createEtudiant(data);
                        //System.out.println("Étudiant ID : " + etudiantId);

                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Étudiant a été créé avec succès.");
                        loadData(); // Recharger les données dans la table
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la création de l'étudiant.");
                }
            } else {
                pc.closePopup();
            }
        }), option);
    }//GEN-LAST:event_cmdNewActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        List<ModelEtudiant> list = getSelectedData(); // Récupérer les étudiants sélectionnés
        if (!list.isEmpty()) {
            DefaultOption option = new DefaultOption() {
                @Override
                public boolean closeWhenClickOutside() {
                    return true;
                }
            };

            String actions[] = new String[]{"Cancel", "Delete"};
            JLabel label = new JLabel("Are you sure to delete " + list.size() + " étudiant(s)?");
            label.setBorder(new javax.swing.border.EmptyBorder(0, 25, 0, 25));

            GlassPanePopup.showPopup(new SimplePopupBorder(label, "Confirm Delete", actions, (pc, i) -> {
                if (i == 1) { // Si l'utilisateur clique sur "Delete"
                    try {
                        for (ModelEtudiant etudiant : list) {
                            System.out.println("Suppression de l'étudiant avec ID : " + etudiant.getEtudiantId()); // Log pour vérifier l'ID
                            serviceEtudiant.deleteEtudiant(etudiant.getEtudiantId()); // Supprimer l'étudiant
                        }
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Étudiant(s) supprimé(s) avec succès.");
                        loadData(); // Recharger les données dans le tableau
                    } catch (Exception e) {
                        e.printStackTrace();
                        Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la suppression des étudiants.");
                    }
                } else {
                    pc.closePopup(); // Fermer le popup si "Cancel" est cliqué
                }
            }), option);
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner un ou plusieurs étudiants à supprimer.");
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchData(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gag.swing.ButtonAction cmdDelete;
    private com.gag.swing.ButtonAction cmdEdit;
    private com.gag.swing.ButtonAction cmdNew;
    private javax.swing.JTable etudiantTable;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
