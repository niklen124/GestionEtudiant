package com.gag.service;

import com.gag.connection.DatabaseConnection;
import java.sql.SQLException;
import com.gag.model.ModelEnseignant;
import com.gag.model.ModelDepartement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceEnseignant {
    
    private ServiceDepartement serviceDepartement;
    private final Connection con;

    public ServiceEnseignant() {
        con = DatabaseConnection.getInstance().getConnection();
    }
    
    public List<ModelEnseignant> getAllEnseignants() throws SQLException {
        List<ModelEnseignant> enseignants = new ArrayList<>();
        String query = "SELECT enseignants.enseignantId, enseignants.name AS enseignantName, enseignants.userName, enseignants.email, enseignants.grade, departements.departementId, departements.name AS departementName " +
                       "FROM enseignants " +
                       "JOIN departements ON enseignants.departementId = departements.departementId " +
                       "ORDER BY enseignants.name";

        try (PreparedStatement p = con.prepareStatement(query);
             ResultSet r = p.executeQuery()) {

            while (r.next()) {
                // Créer un objet ModelDepartement pour le département
                ModelDepartement departement = new ModelDepartement(
                    r.getInt("departementId"),
                    r.getString("departementName") // Nom du département
                );

                // Créer un objet ModelEnseignant pour l'enseignant
                ModelEnseignant enseignant = new ModelEnseignant(
                    r.getInt("enseignantId"),
                    r.getString("enseignantName"), // Nom de l'enseignant
                    r.getString("userName"),
                    r.getString("email"),
                    r.getString("grade"),
                    departement // Associer le département
                );

                // Ajouter l'enseignant à la liste
                enseignants.add(enseignant);
            }
        }
        return enseignants;
    }
    
    public List<ModelEnseignant> searchEnseignants(String search) throws SQLException {
        List<ModelEnseignant> enseignants = new ArrayList<>();
        String query = "SELECT enseignants.enseignantId, enseignants.name AS enseignantName, enseignants.userName, enseignants.email, enseignants.grade, departements.departementId, departements.name AS departementName " +
                       "FROM enseignants " +
                       "JOIN departements ON enseignants.departementId = departements.departementId " +
                       "WHERE (enseignants.name LIKE ? OR enseignants.email LIKE ? OR enseignants.grade LIKE ? OR departements.name LIKE ?) " +
                       "ORDER BY enseignants.name";

        try (PreparedStatement p = con.prepareStatement(query)) {
            // Définir les paramètres pour la recherche
            String searchPattern = "%" + search + "%";
            p.setString(1, searchPattern);
            p.setString(2, searchPattern);
            p.setString(3, searchPattern);
            p.setString(4, searchPattern);

            // Exécuter la requête
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    // Créer un objet ModelDepartement pour le département
                    ModelDepartement departement = new ModelDepartement(
                        r.getInt("departementId"),
                        r.getString("departementName") // Nom du département
                    );

                    // Créer un objet ModelEnseignant pour l'enseignant
                    ModelEnseignant enseignant = new ModelEnseignant(
                        r.getInt("enseignantId"),
                        r.getString("enseignantName"), // Nom de l'enseignant
                        r.getString("userName"),
                        r.getString("email"),
                        r.getString("grade"),
                        departement // Associer le département
                    );

                    // Ajouter l'enseignant à la liste
                    enseignants.add(enseignant);
                }
            }
        }
        return enseignants;
    }
    
    public void createEnseignant(ModelEnseignant data) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "INSERT INTO enseignants (name, userName, email, grade, departementId) VALUES (?, ?, ?, ?, ?)", 
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        p.setString(1, data.getName());
        p.setString(2, data.getUserName());
        p.setString(3, data.getEmail());
        p.setString(4, data.getGrade());
        p.setInt(5, data.getDepartement().getDepartementId());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if (r.next()) {
            int enseignantId = r.getInt(1);
            data.setEnseignantId(enseignantId);
        }
        r.close();
        p.close();
        
    }
    
    public void editEnseignant(ModelEnseignant data) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "update enseignants set name=?, userName=?, email=?, grade=?, departementId=? where enseignantId=? limit 1", 
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        p.setString(1, data.getName());
        p.setString(2, data.getUserName());
        p.setString(3, data.getEmail());
        p.setString(4, data.getGrade());
        p.setInt(5, data.getDepartement().getDepartementId());
        p.setInt(6, data.getEnseignantId());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if (r.next()) {
            int enseignantId = r.getInt(1);
            data.setEnseignantId(enseignantId);
        }
        r.close();
        p.close();
        
    }
    
    public void deleteEnseignant(int enseignantId) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "DELETE FROM enseignants WHERE enseignantId = ?"
        );
        p.setInt(1, enseignantId);
        p.execute();
        p.close();
    }

    public ServiceDepartement getServiceDepartement() {
        if (serviceDepartement == null) { 
            serviceDepartement = new ServiceDepartement();
        }
        return serviceDepartement;
    }
}
