package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelLogin;
import com.gag.model.ModelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceUser {

    private final Connection con;

    public ServiceUser() {
        con = DatabaseConnection.getInstance().getConnection();
    }
    
    public ModelUser login(ModelLogin login) throws SQLException {
        ModelUser data = null;
        PreparedStatement p=con.prepareStatement("select UserID, UserName, Email from `user` where BINARY(Email)=? and BINARY(`Password`)=? limit 1");
        p.setString(1, login.getEmail());
        p.setString(2, login.getPassword());
        ResultSet r=p.executeQuery();
        if (r.next()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String email = r.getString(3);
            data = new ModelUser(userID, userName, email, "");
        }
        r.close();
        p.close();
        return data;
    }

    public void insertUser(ModelUser user) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "INSERT INTO `user` (UserName, Email, `Password`) VALUES (?, ?, ?)", 
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        p.setString(1, user.getUserName());
        p.setString(2, user.getEmail());
        p.setString(3, user.getPassword());
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
            "SELECT UserID FROM `user` WHERE UserName = ? LIMIT 1"
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
            "SELECT UserID FROM `user` WHERE Email = ? LIMIT 1"
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
}

