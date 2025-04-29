package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelMessageRapport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServiceMessage {

    private Connection con;

    public ServiceMessage() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public boolean enregistrerMessage(ModelMessageRapport message) {
        String sql = "INSERT INTO messages (yourName, yourEmail, yourSubject, yourPhone, yourMessage) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setString(1, message.getYourName());
            p.setString(2, message.getYourEmail());
            p.setString(3, message.getYourSubject());
            p.setString(4, message.getYourPhone());
            p.setString(5, message.getYourMessage());
            int rows = p.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            // Ici tu peux utiliser Notifications si tu veux afficher une erreur à l'utilisateur
            // Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur lors de l'enregistrement du message : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
