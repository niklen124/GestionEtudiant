package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelAnneeUniversitaire;
import com.gag.model.ModelEtudiant;
import com.gag.model.ModelFiliere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceEtudiant {
    
    private ServiceFiliere serviceFiliere;
    private ServiceAnneeUniversitaire serviceAnneeUniversitaire;
    private Connection con;

    public ServiceEtudiant() {
        con = DatabaseConnection.getInstance().getConnection();
    }
    
    public int createEtudiant(ModelEtudiant etudiant) throws SQLException {
        String query = "INSERT INTO etudiants (matricule, name, userName, email, telephone, dateNaissance, sexe, anneeUniversitaireId, filiereId) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement p = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            p.setString(1, etudiant.getMatricule());
            p.setString(2, etudiant.getName());
            p.setString(3, etudiant.getUserName());
            p.setString(4, etudiant.getEmail());
            p.setString(5, etudiant.getTelephone());
            p.setDate(6, new java.sql.Date(etudiant.getDateNaissance().getTime()));
            p.setString(7, etudiant.getSexe());
            p.setInt(8, etudiant.getAnneeUniversitaire().getAnneeUniversitaireId());
            p.setInt(9, etudiant.getFiliere().getFiliereId());

            p.executeUpdate();
            ResultSet generatedKeys = p.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Retourne l'ID de l'étudiant créé
            }
        }
        return 0; // Retourne 0 si l'insertion échoue
    }
    
    public List<ModelEtudiant> getAllEtudiant() throws SQLException {
        List<ModelEtudiant> etudiants = new ArrayList<>();
        String query = "SELECT " +
                       "e.etudiantId, " +
                       "e.matricule, " +
                       "e.name AS etudiantName, " +
                       "e.userName, " +
                       "e.email, " +
                       "e.telephone, " +
                       "e.dateNaissance, " +
                       "e.sexe, " +
                       "f.filiereId, " +
                       "f.name AS filiereName, " +
                       "au.anneeUniversitaireId, " +
                       "au.debut AS anneeDebut, " +
                       "au.fin AS anneeFin, " +
                       "au.libelle AS anneeLibelle " +
                       "FROM etudiants e " +
                       "LEFT JOIN filieres f ON e.filiereId = f.filiereId " +
                       "LEFT JOIN annees_universitaires au ON e.anneeUniversitaireId = au.anneeUniversitaireId";

        try (PreparedStatement p = con.prepareStatement(query);
             ResultSet r = p.executeQuery()) {
            while (r.next()) {
                ModelAnneeUniversitaire anneeUniversitaire = new ModelAnneeUniversitaire(
                    r.getInt("anneeUniversitaireId"),
                    r.getInt("anneeDebut"),
                    r.getInt("anneeFin"),
                    r.getString("anneeLibelle")
                );

                ModelFiliere filiere = new ModelFiliere(
                    r.getInt("filiereId"),
                    r.getString("filiereName"),
                    null
                );

                ModelEtudiant etudiant = new ModelEtudiant(
                    r.getInt("etudiantId"),
                    r.getString("matricule"),
                    r.getString("etudiantName"),
                    r.getString("userName"),
                    r.getString("email"),
                    r.getString("telephone"),
                    r.getDate("dateNaissance"),
                    r.getString("sexe"),
                    anneeUniversitaire,
                    filiere
                );

                etudiants.add(etudiant);
            }
        }
        //System.out.println("Étudiants récupérés : " + etudiants.size());
        return etudiants;
    }
    
    public List<ModelEtudiant> searchEtudiant(String search) throws SQLException {
        List<ModelEtudiant> etudiants = new ArrayList<>();
        String query = "SELECT " +
                       "e.etudiantId, " +
                       "e.matricule, " +
                       "e.name AS etudiantName, " +
                       "e.userName, " +
                       "e.email, " +
                       "e.telephone, " +
                       "e.dateNaissance, " +
                       "e.sexe, " +
                       "f.filiereId, " +
                       "f.name AS filiereName, " +
                       "au.anneeUniversitaireId, " +
                       "au.debut AS anneeDebut, " +
                       "au.fin AS anneeFin, " +
                       "au.libelle AS anneeLibelle " +
                       "FROM etudiants e " +
                       "LEFT JOIN filieres f ON e.filiereId = f.filiereId " +
                       "LEFT JOIN annees_universitaires au ON e.anneeUniversitaireId = au.anneeUniversitaireId " +
                       "WHERE (e.name LIKE ? OR e.email LIKE ? OR e.matricule LIKE ? OR f.name LIKE ?) " +
                       "ORDER BY e.name";

        try (PreparedStatement p = con.prepareStatement(query)) {
            String searchPattern = "%" + search + "%";
            p.setString(1, searchPattern);
            p.setString(2, searchPattern);
            p.setString(3, searchPattern);
            p.setString(4, searchPattern);

            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    ModelAnneeUniversitaire anneeUniversitaire = new ModelAnneeUniversitaire(
                        r.getInt("anneeUniversitaireId"),
                        r.getInt("anneeDebut"),
                        r.getInt("anneeFin"),
                        r.getString("anneeLibelle")
                    );

                    ModelFiliere filiere = new ModelFiliere(
                        r.getInt("filiereId"),
                        r.getString("filiereName"),
                        null
                    );

                    ModelEtudiant etudiant = new ModelEtudiant(
                        r.getInt("etudiantId"),
                        r.getString("matricule"),
                        r.getString("etudiantName"),
                        r.getString("userName"),
                        r.getString("email"),
                        r.getString("telephone"),
                        r.getDate("dateNaissance"),
                        r.getString("sexe"),
                        anneeUniversitaire,
                        filiere
                    );

                    etudiants.add(etudiant);
                }
            }
        }
        return etudiants;
    }
    
    public void editEtudiant(ModelEtudiant etudiant) throws SQLException {
        String query = "UPDATE etudiants SET " +
                       "matricule = ?, " +
                       "name = ?, " +
                       "userName = ?, " +
                       "email = ?, " +
                       "telephone = ?, " +
                       "dateNaissance = ?, " +
                       "sexe = ?, " +
                       "anneeUniversitaireId = ?, " +
                       "filiereId = ? " +
                       "WHERE etudiantId = ?";

        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setString(1, etudiant.getMatricule());
            p.setString(2, etudiant.getName());
            p.setString(3, etudiant.getUserName());
            p.setString(4, etudiant.getEmail());
            p.setString(5, etudiant.getTelephone());
            p.setDate(6, new java.sql.Date(etudiant.getDateNaissance().getTime()));
            p.setString(7, etudiant.getSexe());
            p.setInt(8, etudiant.getAnneeUniversitaire().getAnneeUniversitaireId());
            p.setInt(9, etudiant.getFiliere().getFiliereId());
            p.setInt(10, etudiant.getEtudiantId());

            p.executeUpdate();
        }
    }
    
    public void deleteEtudiant(int etudiantId) throws SQLException {
        System.out.println("Suppression de l'étudiant avec ID : " + etudiantId); // Log pour vérifier l'ID
        String query = "DELETE FROM etudiants WHERE etudiantId = ?";

        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setInt(1, etudiantId);
            int rowsAffected = p.executeUpdate();
            System.out.println("Nombre de lignes supprimées : " + rowsAffected); // Log pour vérifier si la suppression a réussi
        }
    }
    
    public ServiceAnneeUniversitaire getServiceAnneeUniversitaire() {
        if (serviceAnneeUniversitaire == null) {
            serviceAnneeUniversitaire = new ServiceAnneeUniversitaire();
        }
        return serviceAnneeUniversitaire;
    }

    public ServiceFiliere getServiceFiliere() {
        if (serviceFiliere == null) {
            serviceFiliere = new ServiceFiliere();
        }
        return serviceFiliere;
    }

    public ModelEtudiant getEtudiantByName(String nomEtudiant) throws SQLException {
        String query = "SELECT " +
                       "e.etudiantId, " +
                       "e.matricule, " +
                       "e.name AS etudiantName, " +
                       "e.userName, " +
                       "e.email, " +
                       "e.telephone, " +
                       "e.dateNaissance, " +
                       "e.sexe, " +
                       "f.filiereId, " +
                       "f.name AS filiereName, " +
                       "au.anneeUniversitaireId, " +
                       "au.debut AS anneeDebut, " +
                       "au.fin AS anneeFin, " +
                       "au.libelle AS anneeLibelle " +
                       "FROM etudiants e " +
                       "LEFT JOIN filieres f ON e.filiereId = f.filiereId " +
                       "LEFT JOIN annees_universitaires au ON e.anneeUniversitaireId = au.anneeUniversitaireId " +
                       "WHERE e.name = ?";

        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setString(1, nomEtudiant);

            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    ModelAnneeUniversitaire anneeUniversitaire = new ModelAnneeUniversitaire(
                        r.getInt("anneeUniversitaireId"),
                        r.getInt("anneeDebut"),
                        r.getInt("anneeFin"),
                        r.getString("anneeLibelle")
                    );

                    ModelFiliere filiere = new ModelFiliere(
                        r.getInt("filiereId"),
                        r.getString("filiereName"),
                        null
                    );

                    return new ModelEtudiant(
                        r.getInt("etudiantId"),
                        r.getString("matricule"),
                        r.getString("etudiantName"),
                        r.getString("userName"),
                        r.getString("email"),
                        r.getString("telephone"),
                        r.getDate("dateNaissance"),
                        r.getString("sexe"),
                        anneeUniversitaire,
                        filiere
                    );
                }
            }
        }
        return null; // Retourne null si aucun étudiant n'est trouvé
    }

    public boolean isEmailInEtudiants(String email) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM etudiants WHERE email = ?";
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

    public int getEtudiantIdByEmail(String email) throws SQLException {
        String query = "SELECT etudiantId FROM etudiants WHERE email = ?";
        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setString(1, email);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    return r.getInt("etudiantId");
                }
            }
        }
        return 0; // Retourne 0 si aucun étudiant n'est trouvé
    }
}
