package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelLogin;
import com.gag.model.ModelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser {

    private final Connection con;

    public ServiceUser() {
        con = DatabaseConnection.getInstance().getConnection();
    }
    
    public ModelUser login(ModelLogin login) throws SQLException {
        ModelUser data = null;
        PreparedStatement p = con.prepareStatement(
            "SELECT userID, userName, email, userType FROM users WHERE BINARY(email) = ? AND BINARY(password) = ? LIMIT 1"
        );
        p.setString(1, login.getEmail());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if (r.next()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String email = r.getString(3);
            String userType = r.getString(4);
            data = new ModelUser(userID, userName, email, "", userType);
        }
        r.close();
        p.close();
        return data;
    }

    public void insertUser(ModelUser user) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "INSERT INTO users (userName, email, password, userType) VALUES (?, ?, ?, ?)", 
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        p.setString(1, user.getUserName());
        p.setString(2, user.getEmail());
        p.setString(3, user.getPassword());
        p.setString(4, user.getUserType());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if (r.next()) {
            int userID = r.getInt(1);
            user.setUserID(userID);
        }
        r.close();
        p.close();
    }

    public void updateUser(ModelUser user) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "UPDATE users SET userName = ?, email = ?, password = ?, userType = ? WHERE userID = ?"
        );
        p.setString(1, user.getUserName());
        p.setString(2, user.getEmail());
        p.setString(3, user.getPassword());
        p.setString(4, user.getUserType());
        p.setInt(5, user.getUserID());
        p.execute();
        p.close();
    }

    public void deleteUser(int userID) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "DELETE FROM users WHERE userID = ?"
        );
        p.setInt(1, userID);
        p.execute();
        p.close();
    }

    public boolean checkDuplicateUser(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement(
            "SELECT userID FROM users WHERE userName = ? LIMIT 1"
        );
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean checkDuplicateEmail(String email) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement(
            "SELECT userID FROM users WHERE email = ? LIMIT 1"
        );
        p.setString(1, email);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public List<ModelUser> getAllUsers() throws SQLException {
        List<ModelUser> users = new ArrayList<>();
        String query = "SELECT userID, userName, email, userType FROM users";

        try (PreparedStatement p = con.prepareStatement(query);
             ResultSet r = p.executeQuery()) {

            while (r.next()) {
                ModelUser user = new ModelUser(
                    r.getInt("userID"),
                    r.getString("userName"),
                    r.getString("email"),
                    "", // Vous pouvez ignorer le mot de passe ici
                    r.getString("userType")
                );
                users.add(user);
            }
        }
        return users;
    }

    public List<ModelUser> search(String search) throws SQLException {
        List<ModelUser> users = new ArrayList<>();
        String query = "SELECT userID, userName, email, userType FROM users WHERE (userName LIKE ? OR email LIKE ? OR userType LIKE ?)";

        try (PreparedStatement p = con.prepareStatement(query)) {
            // Définir les paramètres pour la recherche
            String searchPattern = "%" + search + "%";
            p.setString(1, searchPattern);
            p.setString(2, searchPattern);
            p.setString(3, searchPattern);

            // Exécuter la requête
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    // Créer un objet ModelUser pour chaque résultat
                    ModelUser user = new ModelUser(
                        r.getInt("userID"),
                        r.getString("userName"),
                        r.getString("email"),
                        "", // Vous pouvez ignorer le mot de passe ici
                        r.getString("userType")
                    );
                    users.add(user);
                }
            }
        }
        return users;
    }

    public boolean isEmailMatching(String email) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM users WHERE TRIM(LOWER(email)) = TRIM(LOWER(?))";
        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setString(1, email);
            System.out.println("Vérification de l'email : " + email); // Log pour afficher l'email
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    int count = r.getInt("count");
                    System.out.println("Résultat de la requête : " + count); // Log pour afficher le résultat
                    return count > 0;
                }
            }
        }
        return false; // Retourne false si aucune correspondance n'est trouvée
    }

    public boolean isEmailInUsers(String email) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM users WHERE email = ?";
        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setString(1, email);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    return r.getInt("count") > 0;
                }
            }
        }
        return false;
    }

    public ModelUser getUserByEmail(String email) {
        ModelUser user = null;
        String sql = "SELECT userID, userName, email, userType FROM users WHERE LOWER(email) = LOWER(?) LIMIT 1";
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setString(1, email);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                int userID = r.getInt("userID");
                String userName = r.getString("userName");
                String userEmail = r.getString("email");
                String userType = r.getString("userType");
                user = new ModelUser(userID, userName, userEmail, "", userType);
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

