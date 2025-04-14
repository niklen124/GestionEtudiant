package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelAnneeUniversitaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceAnneeUniversitaire {

    private final Connection con;

    public ServiceAnneeUniversitaire() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void insertAnneeUniversitaire(ModelAnneeUniversitaire annee) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "INSERT INTO annees_universitaires (debut, fin, libelle) VALUES (?, ?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        p.setInt(1, annee.getDebut());
        p.setInt(2, annee.getFin());
        p.setString(3, annee.getLibelle());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if (r.next()) {
            int anneeId = r.getInt(1);
            annee.setAnneeUniversitaireId(anneeId);
        }
        r.close();
        p.close();
    }

    public void updateAnneeUniversitaire(ModelAnneeUniversitaire annee) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "UPDATE annees_universitaires SET debut = ?, fin = ?, libelle = ? WHERE anneeUniversitaireId = ?"
        );
        p.setInt(1, annee.getDebut());
        p.setInt(2, annee.getFin());
        p.setString(3, annee.getLibelle());
        p.setInt(4, annee.getAnneeUniversitaireId());
        p.execute();
        p.close();
    }

    public void deleteAnneeUniversitaire(int anneeId) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "DELETE FROM annees_universitaires WHERE anneeUniversitaireId = ?"
        );
        p.setInt(1, anneeId);
        p.execute();
        p.close();
    }

    public ModelAnneeUniversitaire getAnneeUniversitaireById(int anneeId) throws SQLException {
        ModelAnneeUniversitaire annee = null;
        PreparedStatement p = con.prepareStatement(
            "SELECT anneeUniversitaireId, debut, fin, libelle FROM annees_universitaires WHERE anneeUniversitaireId = ?"
        );
        p.setInt(1, anneeId);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            annee = new ModelAnneeUniversitaire(
                r.getInt("anneeUniversitaireId"),
                r.getInt("debut"),
                r.getInt("fin"),
                r.getString("libelle")
            );
        }
        r.close();
        p.close();
        return annee;
    }

    public List<ModelAnneeUniversitaire> getAllAnneesUniversitaires() throws SQLException {
        List<ModelAnneeUniversitaire> annees = new ArrayList<>();
        String query = "SELECT anneeUniversitaireId, debut, fin, libelle FROM annees_universitaires ORDER BY debut DESC";
        
        try (PreparedStatement p = con.prepareStatement(query);
             ResultSet r = p.executeQuery()) {
            
            while (r.next()) {
                ModelAnneeUniversitaire annee = new ModelAnneeUniversitaire(
                    r.getInt("anneeUniversitaireId"),
                    r.getInt("debut"),
                    r.getInt("fin"),
                    r.getString("libelle")
                );
                annees.add(annee);
            }
        }
        return annees;
    }

    public ModelAnneeUniversitaire getAnneeUniversitaireActuelle() throws SQLException {
        ModelAnneeUniversitaire annee = null;
        PreparedStatement p = con.prepareStatement(
            "SELECT anneeUniversitaireId, debut, fin, libelle FROM annees_universitaires " +
            "WHERE debut <= YEAR(CURRENT_DATE) AND fin >= YEAR(CURRENT_DATE) " +
            "ORDER BY debut DESC LIMIT 1"
        );
        ResultSet r = p.executeQuery();
        if (r.next()) {
            annee = new ModelAnneeUniversitaire(
                r.getInt("anneeUniversitaireId"),
                r.getInt("debut"),
                r.getInt("fin"),
                r.getString("libelle")
            );
        }
        r.close();
        p.close();
        return annee;
    }
} 