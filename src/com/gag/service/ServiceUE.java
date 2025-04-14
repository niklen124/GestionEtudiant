package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelUE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceUE {

    private final Connection con;

    public ServiceUE() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void insertUE(ModelUE ue) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "INSERT INTO ues (code, name, semestreId, filiereId) VALUES (?, ?, ?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        p.setString(1, ue.getCode());
        p.setString(2, ue.getName());
        p.setInt(3, ue.getSemestreId());
        p.setInt(4, ue.getFiliereId());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if (r.next()) {
            int ueId = r.getInt(1);
            ue.setUeId(ueId);
        }
        r.close();
        p.close();
    }

    public void updateUE(ModelUE ue) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "UPDATE ues SET code = ?, name = ?, semestreId = ?, filiereId = ? WHERE ueId = ?"
        );
        p.setString(1, ue.getCode());
        p.setString(2, ue.getName());
        p.setInt(3, ue.getSemestreId());
        p.setInt(4, ue.getFiliereId());
        p.setInt(5, ue.getUeId());
        p.execute();
        p.close();
    }

    public void deleteUE(int ueId) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "DELETE FROM ues WHERE ueId = ?"
        );
        p.setInt(1, ueId);
        p.execute();
        p.close();
    }

    public ModelUE getUEById(int ueId) throws SQLException {
        ModelUE ue = null;
        PreparedStatement p = con.prepareStatement(
            "SELECT ueId, code, name, semestreId, filiereId FROM ues WHERE ueId = ?"
        );
        p.setInt(1, ueId);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            ue = new ModelUE(
                r.getInt("ueId"),
                r.getString("code"),
                r.getString("name"),
                r.getInt("semestreId"),
                r.getInt("filiereId")
            );
        }
        r.close();
        p.close();
        return ue;
    }

    public List<ModelUE> getAllUEs() throws SQLException {
        List<ModelUE> ues = new ArrayList<>();
        String query = "SELECT ueId, code, name, semestreId, filiereId FROM ues";
        
        try (PreparedStatement p = con.prepareStatement(query);
             ResultSet r = p.executeQuery()) {
            
            while (r.next()) {
                ModelUE ue = new ModelUE(
                    r.getInt("ueId"),
                    r.getString("code"),
                    r.getString("name"),
                    r.getInt("semestreId"),
                    r.getInt("filiereId")
                );
                ues.add(ue);
            }
        }
        return ues;
    }

    public List<ModelUE> getUEsByFiliere(int filiereId) throws SQLException {
        List<ModelUE> ues = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(
            "SELECT ueId, code, name, semestreId, filiereId FROM ues WHERE filiereId = ?"
        );
        p.setInt(1, filiereId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            ModelUE ue = new ModelUE(
                r.getInt("ueId"),
                r.getString("code"),
                r.getString("name"),
                r.getInt("semestreId"),
                r.getInt("filiereId")
            );
            ues.add(ue);
        }
        r.close();
        p.close();
        return ues;
    }

    public List<ModelUE> getUEsBySemestre(int semestreId) throws SQLException {
        List<ModelUE> ues = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(
            "SELECT ueId, code, name, semestreId, filiereId FROM ues WHERE semestreId = ?"
        );
        p.setInt(1, semestreId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            ModelUE ue = new ModelUE(
                r.getInt("ueId"),
                r.getString("code"),
                r.getString("name"),
                r.getInt("semestreId"),
                r.getInt("filiereId")
            );
            ues.add(ue);
        }
        r.close();
        p.close();
        return ues;
    }
} 