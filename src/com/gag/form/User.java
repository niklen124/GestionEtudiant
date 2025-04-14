package com.gag.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.gag.component.CreateUser;
import com.gag.model.ModelUser;
import com.gag.service.ServiceUser;
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

public class User extends javax.swing.JPanel {


    public User() {
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
        
        userTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        
        userTable.putClientProperty(FlatClientProperties.STYLE, ""
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
        
        userTable.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(userTable, 0));
        userTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(userTable));
        //userTable.getColumnModel().getColumn(2).setCellRenderer(new ProfileTableRenderer(userTable));
       
    }

    private void loadData() {
        try {
            DefaultTableModel model = (DefaultTableModel) userTable.getModel();

            // Arrêter l'édition si le tableau est en mode édition
            if (userTable.isEditing()) {
                userTable.getCellEditor().stopCellEditing();
            }

            // Effacer les anciennes données
            model.setRowCount(0);

            // Récupérer les utilisateurs via le service
            ServiceUser service = new ServiceUser();
            List<ModelUser> list = service.getAllUsers();

            // Ajouter chaque utilisateur au tableau
            for (ModelUser user : list) {
                model.addRow(new Object[]{
                    false, // Checkbox
                    user.getUserID(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getUserType()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void searchData(String search) {
        try {
            DefaultTableModel model = (DefaultTableModel) userTable.getModel();

            // Arrêter l'édition si le tableau est en mode édition
            if (userTable.isEditing()) {
                userTable.getCellEditor().stopCellEditing();
            }

            // Effacer les anciennes données
            model.setRowCount(0);

            // Récupérer les utilisateurs via le service
            ServiceUser service = new ServiceUser();
            List<ModelUser> list = service.search(search);

            // Ajouter chaque utilisateur au tableau
            for (ModelUser user : list) {
                model.addRow(new Object[]{
                    false, // Checkbox
                    user.getUserID(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getUserType()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        txtSearch = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        cmdEdit = new com.gag.swing.ButtonAction();
        cmdNew = new com.gag.swing.ButtonAction();
        cmdDelete = new com.gag.swing.ButtonAction();

        setBackground(new java.awt.Color(240, 240, 240));

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "#", "Name", "Email", "Password", "User Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(0).setMaxWidth(50);
            userTable.getColumnModel().getColumn(1).setMaxWidth(40);
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

        lbTitle.setText("User List");

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
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 568, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                                .addComponent(cmdNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))))
                .addContainerGap(86, Short.MAX_VALUE))
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
        List<ModelUser> list = getSelectedData(); // Récupérer les utilisateurs sélectionnés
        if (!list.isEmpty()) {
            if (list.size() == 1) {
                // Récupérer l'utilisateur sélectionné
                ModelUser data = list.get(0);

                // Créer une instance de CreateUser et charger les données de l'utilisateur
                CreateUser createUser = new CreateUser();
                createUser.getNameField().setText(data.getUserName());
                createUser.getEmailField().setText(data.getEmail());
                createUser.getPasswordField().setText(data.getPassword());
                createUser.getUserTypeComboBox().setSelectedItem(data.getUserType());

                // Définir les options pour le popup
                DefaultOption option = new DefaultOption() {
                    @Override
                    public boolean closeWhenClickOutside() {
                        return true;
                    }
                };

                // Actions pour le popup
                String actions[] = new String[]{"Cancel", "Update"};
                GlassPanePopup.showPopup(new SimplePopupBorder(createUser, "Edit User [" + data.getUserName() + "]", actions, (pc, i) -> {
                    if (i == 1) { // Si l'utilisateur clique sur "Update"
                        try {
                            // Récupérer les données mises à jour depuis le formulaire
                            String updatedName = createUser.getName();
                            String updatedEmail = createUser.getEmail();
                            String updatedPassword = createUser.getPassword();
                            String updatedUserType = createUser.getUserType();

                            /*// Valider les données
                            if (updatedName.isEmpty() || updatedEmail.isEmpty() || updatedPassword.isEmpty()) {
                                Notifications.getInstance().show(Notifications.Type.ERROR, "All fields are required!");
                                return;
                            }*/

                            // Mettre à jour l'utilisateur
                            data.setUserName(updatedName);
                            data.setEmail(updatedEmail);
                            data.setPassword(updatedPassword);
                            data.setUserType(updatedUserType);

                            // Appeler le service pour mettre à jour l'utilisateur
                            ServiceUser service = new ServiceUser();
                            service.updateUser(data);

                            // Fermer le popup et afficher une notification de succès
                            pc.closePopup();
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, "User has been updated successfully!");

                            // Recharger les données dans le tableau
                            loadData();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Notifications.getInstance().show(Notifications.Type.ERROR, "Failed to update user!");
                        }
                    } else {
                        pc.closePopup(); // Fermer le popup si "Cancel" est cliqué
                    }
                }), option);
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Please select only one user");
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Please select a user to edit");
        }
    }//GEN-LAST:event_cmdEditActionPerformed

    private List<ModelUser> getSelectedData() {
        List<ModelUser> list = new ArrayList<>();
        for (int i = 0; i < userTable.getRowCount(); i++) {
            if ((boolean) userTable.getValueAt(i, 0)) { // Vérifie si la checkbox est cochée
                // Récupérer les données de la ligne
                int userID = (int) userTable.getValueAt(i, 1);
                String userName = (String) userTable.getValueAt(i, 2);
                String email = (String) userTable.getValueAt(i, 3);
                String password = (String) userTable.getValueAt(i, 4);
                String userType = (String) userTable.getValueAt(i, 5);

                // Créer un objet ModelUser avec les données récupérées
                ModelUser data = new ModelUser(userID, userName, email, password, userType);
                list.add(data);
            }
        }
        return list;
    }
    
    private void cmdNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNewActionPerformed
        // Créer une instance de CreateUser
        CreateUser createUser = new CreateUser();

        // Charger les données dans le formulaire (si nécessaire)
        ServiceUser service = new ServiceUser();

        // Définir les options pour le popup
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };

        // Actions pour le popup
        String actions[] = new String[]{"Cancel", "Save"};
        GlassPanePopup.showPopup(new SimplePopupBorder(createUser, "Create User", actions, (pc, i) -> {
            if (i == 1) { // Si l'utilisateur clique sur "Save"
                try {
                    // Récupérer les données du formulaire
                    String name = createUser.getName();
                    String email = createUser.getEmail();
                    String password = createUser.getPassword();
                    String userType = createUser.getUserType();

                    // Valider les données
                    if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        Notifications.getInstance().show(Notifications.Type.ERROR, "All fields are required!");
                        return;
                    }

                    // Créer un nouvel utilisateur
                    ModelUser user = new ModelUser(0, name, email, password, userType);
                    service.insertUser(user);

                    // Fermer le popup et afficher une notification de succès
                    pc.closePopup();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "User has been created successfully!");

                    // Recharger les données dans le tableau
                    loadData();
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Failed to create user!");
                }
            } else {
                pc.closePopup(); // Fermer le popup si "Cancel" est cliqué
            }
        }), option);
    }//GEN-LAST:event_cmdNewActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        List<ModelUser> list = getSelectedData(); // Récupérer les utilisateurs sélectionnés
        if (!list.isEmpty()) {
            // Définir les options pour le popup
            DefaultOption option = new DefaultOption() {
                @Override
                public boolean closeWhenClickOutside() {
                    return true;
                }
            };

            // Définir les actions pour le popup
            String actions[] = new String[]{"Cancel", "Delete"};
            JLabel label = new JLabel("Are you sure to delete " + list.size() + " user(s)?");
            label.setBorder(new javax.swing.border.EmptyBorder(0, 25, 0, 25));

            // Afficher le popup de confirmation
            GlassPanePopup.showPopup(new SimplePopupBorder(label, "Confirm Delete", actions, (pc, i) -> {
                if (i == 1) { // Si l'utilisateur clique sur "Delete"
                    try {
                        // Supprimer chaque utilisateur sélectionné
                        ServiceUser service = new ServiceUser();
                        for (ModelUser user : list) {
                            service.deleteUser(user.getUserID());
                        }

                        // Fermer le popup et afficher une notification de succès
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "User(s) have been deleted successfully!");

                        // Recharger les données dans le tableau
                        loadData();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Notifications.getInstance().show(Notifications.Type.ERROR, "Failed to delete user(s)!");
                    }
                } else {
                    pc.closePopup(); // Fermer le popup si "Cancel" est cliqué
                }
            }), option);
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Please select user(s) to delete");
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchData(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gag.swing.ButtonAction cmdDelete;
    private com.gag.swing.ButtonAction cmdEdit;
    private com.gag.swing.ButtonAction cmdNew;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
