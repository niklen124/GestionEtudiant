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
import com.gag.model.ModelUser;
import com.gag.service.ServiceEtudiant;
import raven.toast.Notifications;
import java.util.ArrayList;
import javax.swing.JLabel;

public class EtudiantNotes extends javax.swing.JPanel {

    ServiceSaisirNotes serviceSaisirNotes = new ServiceSaisirNotes();
    private ModelUser user;

    public EtudiantNotes(ModelUser user) {
        this.user = user;
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
        
        noteTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        
        noteTable.putClientProperty(FlatClientProperties.STYLE, ""
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
        
        noteTable.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(noteTable, 0));
        noteTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(noteTable));
    }
    
    private void loadData() {
        try {
            DefaultTableModel model = (DefaultTableModel) noteTable.getModel();

            // Arrêter l'édition si le tableau est en mode édition
            if (noteTable.isEditing()) {
                noteTable.getCellEditor().stopCellEditing();
            }

            // Effacer les anciennes données
            model.setRowCount(0);

            // Récupérer l'ID de l'étudiant connecté
            ServiceEtudiant serviceEtudiant = new ServiceEtudiant();
            int etudiantId = serviceEtudiant.getEtudiantIdByEmail(user.getEmail());

            if (etudiantId > 0) {
                // Récupérer les notes de l'étudiant connecté
                List<ModelNote> notes = serviceSaisirNotes.getNotesByEtudiantId(etudiantId);

                // Parcourir les notes et ajouter les données au tableau
                for (ModelNote note : notes) {
                    model.addRow(new Object[]{
                        false, // Checkbox non cochée par défaut
                        note.getEtudiant().getName(), // Nom de l'étudiant
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
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Aucun étudiant trouvé avec cet email");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors du chargement des notes");
        }
    }

    private void searchData(String search) {
        try {
            DefaultTableModel model = (DefaultTableModel) noteTable.getModel();

            // Arrêter l'édition si le tableau est en mode édition
            if (noteTable.isEditing()) {
                noteTable.getCellEditor().stopCellEditing();
            }

            // Effacer les anciennes données
            model.setRowCount(0);

            // Récupérer l'ID de l'étudiant connecté
            ServiceEtudiant serviceEtudiant = new ServiceEtudiant();
            int etudiantId = serviceEtudiant.getEtudiantIdByEmail(user.getEmail());

            if (etudiantId > 0) {
                // Récupérer les notes de l'étudiant connecté
                List<ModelNote> notes = serviceSaisirNotes.getNotesByEtudiantId(etudiantId);

                // Filtrer les notes en fonction du texte de recherche et de la filière
                for (ModelNote note : notes) {
                    // Vérifier si la note correspond aux critères de recherche
                    if (note.getModule().getCode().toLowerCase().contains(search.toLowerCase()) ||
                        note.getModule().getName().toLowerCase().contains(search.toLowerCase()) ||
                        (note.getModule().getUe() != null && note.getModule().getUe().getCode().toLowerCase().contains(search.toLowerCase())) ||
                        (note.getModule().getUe() != null && note.getModule().getUe().getName().toLowerCase().contains(search.toLowerCase())) ||
                        String.valueOf(note.getNote()).contains(search)) {
                        
                        // Vérifier si le module appartient à la filière de l'étudiant
                        if (note.getModule().getUe() != null && 
                            note.getModule().getUe().getFiliere() != null && 
                            note.getModule().getUe().getFiliere().getFiliereId() == note.getEtudiant().getFiliere().getFiliereId()) {
                            
                            model.addRow(new Object[]{
                                false, // Checkbox non cochée par défaut
                                note.getEtudiant().getName(), // Nom de l'étudiant
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
                    }
                }
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Aucun étudiant trouvé avec cet email");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de la recherche des notes");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        noteTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        lbTitle = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        setBackground(new java.awt.Color(240, 240, 240));

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scroll.setPreferredSize(new java.awt.Dimension(1500, 400));

        noteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "Nom de l'Etudiant", "Code du Module", "Nom du Module", "Code de Ue", "Nom de Ue", "Filiere", "Departements", "Note", "Séméstre"
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
        noteTable.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(noteTable);
        if (noteTable.getColumnModel().getColumnCount() > 0) {
            noteTable.getColumnModel().getColumn(0).setMaxWidth(50);
            noteTable.getColumnModel().getColumn(1).setMaxWidth(250);
            noteTable.getColumnModel().getColumn(2).setMaxWidth(200);
            noteTable.getColumnModel().getColumn(3).setMaxWidth(400);
            noteTable.getColumnModel().getColumn(4).setMaxWidth(100);
            noteTable.getColumnModel().getColumn(5).setMaxWidth(300);
            noteTable.getColumnModel().getColumn(6).setMaxWidth(200);
            noteTable.getColumnModel().getColumn(7).setMaxWidth(200);
            noteTable.getColumnModel().getColumn(8).setMaxWidth(150);
            noteTable.getColumnModel().getColumn(9).setMaxWidth(100);
        }

        lbTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTitle.setText("Liste des Note de l'Etudiant");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch))
                        .addGap(5, 653, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTable noteTable;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
