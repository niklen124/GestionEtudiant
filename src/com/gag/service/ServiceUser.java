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
}

