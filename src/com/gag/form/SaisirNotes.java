package com.gag.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.gag.component.CreateSaisirNote;
import com.gag.table.CheckBoxTableHeaderRenderer;
import com.gag.table.TableHeaderAlignment;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import java.util.List;
import com.gag.service.ServiceSaisirNotes;
import com.gag.model.ModelNote;
import raven.toast.Notifications;
import java.util.ArrayList;
import javax.swing.JLabel;

public class SaisirNotes extends javax.swing.JPanel {

    ServiceSaisirNotes serviceSaisirNotes = new ServiceSaisirNotes();

    public SaisirNotes() {
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
        
        saisirNoteTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        
        saisirNoteTable.putClientProperty(FlatClientProperties.STYLE, ""
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
        
        saisirNoteTable.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(saisirNoteTable, 0));
        saisirNoteTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(saisirNoteTable));
    }
    
    private void loadData() {
        try {
            DefaultTableModel model = (DefaultTableModel) saisirNoteTable.getModel();

            // Arrêter l'édition si le tableau est en mode édition
            if (saisirNoteTable.isEditing()) {
                saisirNoteTable.getCellEditor().stopCellEditing();
            }

            // Effacer les anciennes données
            model.setRowCount(0);

            // Récupérer les notes via le service
            ServiceSaisirNotes serviceSaisirNotes = new ServiceSaisirNotes();
            List<ModelNote> notes = serviceSaisirNotes.getAllNotes();

            // Parcourir les notes et ajouter les données au tableau
            for (ModelNote note : notes) {
                model.addRow(new Object[]{
                    false, // Checkbox non cochée par défaut
                    note.getEtudiant().getName(), // Nom de l'étudiant
                    note.getModule().getEnseignant() != null ? note.getModule().getEnseignant().getName() : "", // Nom de l'enseignant
                    note.getModule().getCode(), // Code du module
                    note.getModule().getName(), // Nom du module
                    note.getModule().getUe() != null ? note.getModule().getUe().getCode() : "", // Code de l'UE
                    note.getModule().getUe() != null ? note.getModule().getUe().getName() : "", // Nom de l'UE
                    note.getModule().getUe() != null && note.getModule().getUe().getFiliere() != null ? note.getModule().getUe().getFiliere().getName() : "", // Filière
                    note.getModule().getUe() != null && note.getModule().getUe().getFiliere() != null && note.getModule().getUe().getFiliere().getDepartement() != null ? note.getModule().getUe().getFiliere().getDepartement().getName() : "", // Département
                    note.getNote(), // Note
                    note.getModule().getUe() != null && note.getModule().getUe().getSemestre() != null ? note.getModule().getUe().getSemestre().getName() : "" // Semestre
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchData(String search) {
        try {
            DefaultTableModel model = (DefaultTableModel) saisirNoteTable.getModel();

            // Arrêter l'édition si le tableau est en mode édition
            if (saisirNoteTable.isEditing()) {
                saisirNoteTable.getCellEditor().stopCellEditing();
            }

            // Effacer les anciennes données
            model.setRowCount(0);

            // Récupérer les notes via le service
            ServiceSaisirNotes serviceSaisirNotes = new ServiceSaisirNotes();
            List<ModelNote> notes = serviceSaisirNotes.searchNotes(search);

            // Parcourir les notes et ajouter les données au tableau
            for (ModelNote note : notes) {
                model.addRow(new Object[]{
                    false, // Checkbox non cochée par défaut
                    note.getEtudiant().getName(), // Nom de l'étudiant
                    note.getModule().getEnseignant() != null ? note.getModule().getEnseignant().getName() : "", // Nom de l'enseignant
                    note.getModule().getCode(), // Code du module
                    note.getModule().getName(), // Nom du module
                    note.getModule().getUe() != null ? note.getModule().getUe().getCode() : "", // Code de l'UE
                    note.getModule().getUe() != null ? note.getModule().getUe().getName() : "", // Nom de l'UE
                    note.getModule().getUe() != null && note.getModule().getUe().getFiliere() != null ? note.getModule().getUe().getFiliere().getName() : "", // Filière
                    note.getModule().getUe() != null && note.getModule().getUe().getFiliere() != null && note.getModule().getUe().getFiliere().getDepartement() != null ? note.getModule().getUe().getFiliere().getDepartement().getName() : "", // Département
                    note.getNote(), // Note
                    note.getModule().getUe() != null && note.getModule().getUe().getSemestre() != null ? note.getModule().getUe().getSemestre().getName() : "" // Semestre
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<ModelNote> getSelectedData() {
        List<ModelNote> list = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) saisirNoteTable.getModel();
        int rows = model.getRowCount();

        try {
            // Récupérer toutes les notes de la base de données
            List<ModelNote> allNotes = serviceSaisirNotes.getAllNotes();

            for (int i = 0; i < rows; i++) {
                boolean selected = (boolean) model.getValueAt(i, 0); // Vérifier si la ligne est sélectionnée
                if (selected) {
                    // Récupérer les données du tableau
                    String nomEtudiant = (String) model.getValueAt(i, 1);
                    String nomEnseignant = (String) model.getValueAt(i, 2);
                    String codeModule = (String) model.getValueAt(i, 3);
                    String nomModule = (String) model.getValueAt(i, 4);

                    // Trouver la note correspondante dans la liste des notes
                    for (ModelNote note : allNotes) {
                        if (note.getEtudiant().getName().trim().equalsIgnoreCase(nomEtudiant.trim())
                            && note.getModule().getCode().trim().equalsIgnoreCase(codeModule.trim())
                            && note.getModule().getName().trim().equalsIgnoreCase(nomModule.trim())) {
                            list.add(note);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        saisirNoteTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        lbTitle = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdNew = new com.gag.swing.ButtonAction();
        cmdEdit = new com.gag.swing.ButtonAction();
        cmdDelete = new com.gag.swing.ButtonAction();

        setBackground(new java.awt.Color(240, 240, 240));

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scroll.setPreferredSize(new java.awt.Dimension(1500, 400));

        saisirNoteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "Nom de l'Etudiant", "Nom de l'Enseignant", "Code du Module", "Nom du Module", "Code de Ue", "Nom de Ue", "Filiere", "Departements", "Note", "Séméstre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        saisirNoteTable.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(saisirNoteTable);
        if (saisirNoteTable.getColumnModel().getColumnCount() > 0) {
            saisirNoteTable.getColumnModel().getColumn(0).setMaxWidth(50);
            saisirNoteTable.getColumnModel().getColumn(1).setMaxWidth(250);
            saisirNoteTable.getColumnModel().getColumn(2).setMaxWidth(250);
            saisirNoteTable.getColumnModel().getColumn(3).setMaxWidth(200);
            saisirNoteTable.getColumnModel().getColumn(4).setMaxWidth(400);
            saisirNoteTable.getColumnModel().getColumn(5).setMaxWidth(100);
            saisirNoteTable.getColumnModel().getColumn(6).setMaxWidth(300);
            saisirNoteTable.getColumnModel().getColumn(7).setMaxWidth(200);
            saisirNoteTable.getColumnModel().getColumn(8).setMaxWidth(200);
            saisirNoteTable.getColumnModel().getColumn(9).setMaxWidth(150);
            saisirNoteTable.getColumnModel().getColumn(10).setMaxWidth(100);
        }

        lbTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTitle.setText("Liste des Note des Etudiants");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cmdNew.setText("New");
        cmdNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNewActionPerformed(evt);
            }
        });

        cmdEdit.setText("Edit");
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
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
                            .addComponent(lbTitle)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(378, 378, Short.MAX_VALUE)
                        .addComponent(cmdNew, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchData(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cmdNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNewActionPerformed
        CreateSaisirNote createSaisirNote = new CreateSaisirNote();
        createSaisirNote.loadData(new ServiceSaisirNotes());
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Cancel", "Save"};
        GlassPanePopup.showPopup(new SimplePopupBorder(createSaisirNote, "Créer une Note", actions, (pc, i) -> {
            if (i == 1) { // Si l'utilisateur clique sur "Save"
                try {
                    // Récupérer les données saisies
                    ModelNote note = createSaisirNote.getData();
                    if (note == null) {
                        // Si les données sont invalides, arrêter le processus
                        return;
                    }

                    // Insérer la note dans la base de données
                    ServiceSaisirNotes serviceSaisirNotes = new ServiceSaisirNotes();
                    serviceSaisirNotes.insertNote(note);

                    // Fermer la popup et afficher une notification de succès
                    pc.closePopup();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "La note a été créée avec succès.");
                    loadData(); // Rafraîchir les données affichées dans le tableau
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la création de la note.");
                }
            } else {
                pc.closePopup(); // Fermer la popup si l'utilisateur clique sur "Cancel"
            }
        }), option);
    }//GEN-LAST:event_cmdNewActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        List<ModelNote> list = getSelectedData(); // Récupérer les notes sélectionnées
        if (!list.isEmpty()) {
            DefaultOption option = new DefaultOption() {
                @Override
                public boolean closeWhenClickOutside() {
                    return true;
                }
            };

            String actions[] = new String[]{"Cancel", "Delete"};
            JLabel label = new JLabel("Êtes-vous sûr de vouloir supprimer " + list.size() + " note(s) ?");
            label.setBorder(new javax.swing.border.EmptyBorder(0, 25, 0, 25));

            GlassPanePopup.showPopup(new SimplePopupBorder(label, "Confirmer la suppression", actions, (pc, i) -> {
                if (i == 1) { // Si l'utilisateur clique sur "Delete"
                    try {
                        for (ModelNote note : list) {
                            serviceSaisirNotes.deleteNote(note.getNoteId()); // Supprimer la note
                        }
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Note(s) supprimée(s) avec succès.");
                        loadData(); // Recharger les données dans le tableau
                    } catch (Exception e) {
                        e.printStackTrace();
                        Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la suppression des notes.");
                    }
                } else {
                    pc.closePopup(); // Fermer le popup si "Cancel" est cliqué
                }
            }), option);
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner une ou plusieurs notes à supprimer.");
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        List<ModelNote> list = getSelectedData(); // Récupérer les notes sélectionnées
        if (!list.isEmpty()) {
            if (list.size() == 1) {
                ModelNote note = list.get(0); // Récupérer la note sélectionnée
                CreateSaisirNote createSaisirNote = new CreateSaisirNote();
                createSaisirNote.loadData(serviceSaisirNotes, note); // Charger les données de la note dans le formulaire

                DefaultOption option = new DefaultOption() {
                    @Override
                    public boolean closeWhenClickOutside() {
                        return true;
                    }
                };

                String actions[] = new String[]{"Cancel", "Update"};
                GlassPanePopup.showPopup(new SimplePopupBorder(createSaisirNote, "Modifier Note [" + note.getEtudiant().getName() + "]", actions, (pc, i) -> {
                    if (i == 1) { // Si l'utilisateur clique sur "Update"
                        try {
                            ModelNote dataEdit = createSaisirNote.getData(); // Récupérer les données mises à jour
                            if (dataEdit != null) {
                                if (note.getInscription() == null) {
                                    Notifications.getInstance().show(Notifications.Type.WARNING, "L'inscription est manquante pour cette note.");
                                    return; // Arrêter le processus si l'inscription est null
                                }
                                dataEdit.setInscriptionId(note.getInscriptionId()); // Conserver l'ID de l'inscription
                                dataEdit.setNoteId(note.getNoteId()); // Conserver l'ID de la note
                                serviceSaisirNotes.editNote(dataEdit); // Mettre à jour la note dans la base de données
                                pc.closePopup();
                                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Note modifiée avec succès.");
                                loadData(); // Recharger les données dans le tableau
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la modification de la note.");
                        }
                    } else {
                        pc.closePopup(); // Fermer le popup si "Cancel" est cliqué
                    }
                }), option);
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner une seule note.");
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Veuillez sélectionner une note à modifier.");
        }
    }//GEN-LAST:event_cmdEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gag.swing.ButtonAction cmdDelete;
    private com.gag.swing.ButtonAction cmdEdit;
    private com.gag.swing.ButtonAction cmdNew;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTable saisirNoteTable;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
