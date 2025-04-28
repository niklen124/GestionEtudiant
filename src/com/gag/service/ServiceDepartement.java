package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelDepartement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDepartement {

    private final Connection con;

    public ServiceDepartement() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void insertDepartement(ModelDepartement departement) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "INSERT INTO departements (name) VALUES (?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        p.setString(1, departement.getName());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if (r.next()) {
            int departementId = r.getInt(1);
            departement.setDepartementId(departementId);
        }
        r.close();
        p.close();
    }

    public void updateDepartement(ModelDepartement departement) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "UPDATE departements SET name = ? WHERE departementId = ?"
        );
        p.setString(1, departement.getName());
        p.setInt(2, departement.getDepartementId());
        p.execute();
        p.close();
    }

    public void deleteDepartement(int departementId) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "DELETE FROM departements WHERE departementId = ?"
        );
        p.setInt(1, departementId);
        p.execute();
        p.close();
    }

    public ModelDepartement getDepartementById(int departementId) throws SQLException {
        ModelDepartement departement = null;
        PreparedStatement p = con.prepareStatement(
            "SELECT departementId, name FROM departements WHERE departementId = ?"
        );
        p.setInt(1, departementId);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            departement = new ModelDepartement(
                r.getInt("departementId"),
                r.getString("name")
            );
        }
        r.close();
        p.close();
        return departement;
    }

    public List<ModelDepartement> getAllDepartements() throws SQLException {
        List<ModelDepartement> departements = new ArrayList<>();
        String query = "SELECT departementId, name FROM departements";
        
        try (PreparedStatement p = con.prepareStatement(query);
             ResultSet r = p.executeQuery()) {
            
            while (r.next()) {
                ModelDepartement departement = new ModelDepartement(
                    r.getInt("departementId"),
                    r.getString("name")
                );
                departements.add(departement);
            }
        }
        return departements;
    }

    public boolean verifierEtudiantExiste(String nomEtudiant) throws SQLException {
        String query = "SELECT COUNT(*) FROM etudiants WHERE name = ?";
        try (PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            p.setString(1, nomEtudiant);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                return r.getInt(1) > 0;
            }
            return false;
        }
    }
}