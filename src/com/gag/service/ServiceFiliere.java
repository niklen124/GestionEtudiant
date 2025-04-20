package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelDepartement;
import com.gag.model.ModelFiliere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceFiliere {

    private final Connection con;

    public ServiceFiliere() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public List<ModelFiliere> getAllFilieres() throws SQLException {
        List<ModelFiliere> filieres = new ArrayList<>();
        String query = "SELECT filieres.filiereId, filieres.name AS filiereName, departements.departementId, departements.name AS departementName " +
                       "FROM filieres " +
                       "JOIN departements ON filieres.departementId = departements.departementId " +
                       "ORDER BY filieres.name";

        try (PreparedStatement p = con.prepareStatement(query);
             ResultSet r = p.executeQuery()) {

            while (r.next()) {
                // Créer un objet ModelDepartement pour le département
                ModelDepartement departement = new ModelDepartement(
                    r.getInt("departementId"),
                    r.getString("departementName") // Nom du département
                );

                // Créer un objet ModelFiliere pour la filière
                ModelFiliere filiere = new ModelFiliere(
                    r.getInt("filiereId"),
                    r.getString("filiereName"), // Nom de la filière
                    departement // Associer le département
                );

                // Ajouter la filière à la liste
                filieres.add(filiere);
            }
        }
        return filieres;
    }

    public List<ModelFiliere> searchFilieres(String search) throws SQLException {
        List<ModelFiliere> filieres = new ArrayList<>();
        String query = "SELECT filieres.filiereId, filieres.name AS filiereName, departements.departementId, departements.name AS departementName " +
                       "FROM filieres " +
                       "JOIN departements ON filieres.departementId = departements.departementId " +
                       "WHERE (filieres.name LIKE ? OR departements.name LIKE ?) " +
                       "ORDER BY filieres.name";

        try (PreparedStatement p = con.prepareStatement(query)) {
            // Définir les paramètres pour la recherche
            String searchPattern = "%" + search + "%";
            p.setString(1, searchPattern);
            p.setString(2, searchPattern);

            // Exécuter la requête
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    // Créer un objet ModelDepartement pour le département
                    ModelDepartement departement = new ModelDepartement(
                        r.getInt("departementId"),
                        r.getString("departementName") // Nom du département
                    );

                    // Créer un objet ModelFiliere pour la filière
                    ModelFiliere filiere = new ModelFiliere(
                        r.getInt("filiereId"),
                        r.getString("filiereName"), // Nom de la filière
                        departement // Associer le département
                    );

                    // Ajouter la filière à la liste
                    filieres.add(filiere);
                }
            }
        }
        return filieres;
    }

    public void createFiliere(ModelFiliere data) throws SQLException {
        // Vérifier si le département existe déjà
        int departementId = getOrCreateDepartement(data.getDepartement());

        // Insérer la filière avec l'ID du département
        PreparedStatement p = con.prepareStatement(
            "INSERT INTO filieres (name, departementId) VALUES (?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        p.setString(1, data.getName());
        p.setInt(2, departementId);
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if (r.next()) {
            int filiereId = r.getInt(1);
            data.setFiliereId(filiereId);
        }
        r.close();
        p.close();
    }

    public void editFiliere(ModelFiliere data) throws SQLException {
        // Vérifier ou créer le département
        int departementId = getOrCreateDepartement(data.getDepartement());

        // Mettre à jour la filière avec l'ID du département
        PreparedStatement p = con.prepareStatement(
            "UPDATE filieres SET name = ?, departementId = ? WHERE filiereId = ?"
        );
        p.setString(1, data.getName());
        p.setInt(2, departementId);
        p.setInt(3, data.getFiliereId());
        p.executeUpdate();
        p.close();
    }

    public void deleteFiliere(int filiereId) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "DELETE FROM filieres WHERE filiereId = ?"
        );
        p.setInt(1, filiereId);
        p.executeUpdate();
        p.close();
    }

    public ModelFiliere getFiliereById(int filiereId) throws SQLException {
        ModelFiliere filiere = null;
        PreparedStatement p = con.prepareStatement(
            "SELECT filieres.filiereId, filieres.name AS filiereName, departements.departementId, departements.name AS departementName " +
            "FROM filieres " +
            "JOIN departements ON filieres.departementId = departements.departementId " +
            "WHERE filieres.filiereId = ?"
        );
        p.setInt(1, filiereId);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            // Créer un objet ModelDepartement pour le département
            ModelDepartement departement = new ModelDepartement(
                r.getInt("departementId"),
                r.getString("departementName") // Nom du département
            );

            // Créer un objet ModelFiliere pour la filière
            filiere = new ModelFiliere(
                r.getInt("filiereId"),
                r.getString("filiereName"), // Nom de la filière
                departement // Associer le département
            );
        }
        r.close();
        p.close();
        return filiere;
    }

    public List<ModelFiliere> getFilieresByDepartement(int departementId) throws SQLException {
        List<ModelFiliere> filieres = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(
            "SELECT filieres.filiereId, filieres.name AS filiereName, departements.departementId, departements.name AS departementName " +
            "FROM filieres " +
            "JOIN departements ON filieres.departementId = departements.departementId " +
            "WHERE filieres.departementId = ?"
        );
        p.setInt(1, departementId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            // Créer un objet ModelDepartement pour le département
            ModelDepartement departement = new ModelDepartement(
                r.getInt("departementId"),
                r.getString("departementName") // Nom du département
            );

            // Créer un objet ModelFiliere pour la filière
            ModelFiliere filiere = new ModelFiliere(
                r.getInt("filiereId"),
                r.getString("filiereName"), // Nom de la filière
                departement // Associer le département
            );

            // Ajouter la filière à la liste
            filieres.add(filiere);
        }
        r.close();
        p.close();
        return filieres;
    }

    public List<ModelFiliere> getFilieresByDepartementId(int departementId) throws SQLException {
        List<ModelFiliere> filieres = new ArrayList<>();
        String query = "SELECT filiereId, name FROM filieres WHERE departementId = ? ORDER BY name";

        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setInt(1, departementId);
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    ModelFiliere filiere = new ModelFiliere(
                        r.getInt("filiereId"),
                        r.getString("name"),
                        null // Pas besoin de charger le département ici
                    );
                    System.out.println("Filière trouvée dans la base de données : " + filiere.getName());
                    filieres.add(filiere);
                }
            }
        }
        return filieres;
    }

    private int getOrCreateDepartement(ModelDepartement departement) throws SQLException {
        // Vérifier si le département existe déjà
        PreparedStatement checkStmt = con.prepareStatement(
            "SELECT departementId FROM departements WHERE name = ?"
        );
        checkStmt.setString(1, departement.getName());
        ResultSet r = checkStmt.executeQuery();
        if (r.next()) {
            // Si le département existe, retourner son ID
            int departementId = r.getInt("departementId");
            r.close();
            checkStmt.close();
            return departementId;
        }
        r.close();
        checkStmt.close();

        // Si le département n'existe pas, le créer
        PreparedStatement insertStmt = con.prepareStatement(
            "INSERT INTO departements (name) VALUES (?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        insertStmt.setString(1, departement.getName());
        insertStmt.executeUpdate();
        ResultSet generatedKeys = insertStmt.getGeneratedKeys();
        int newDepartementId = 0;
        if (generatedKeys.next()) {
            newDepartementId = generatedKeys.getInt(1);
        }
        generatedKeys.close();
        insertStmt.close();
        return newDepartementId;
    }
}