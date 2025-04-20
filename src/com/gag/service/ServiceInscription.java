package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelAnneeUniversitaire;
import com.gag.model.ModelEtudiant;
import com.gag.model.ModelFiliere;
import com.gag.model.ModelInscription;
import com.gag.model.ModelModule;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceInscription {

    private ServiceFiliere serviceFiliere;
    private ServiceAnneeUniversitaire serviceAnneeUniversitaire;
    private Connection con;

    public ServiceInscription() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public List<ModelInscription> getAllInscriptions() throws SQLException {
        List<ModelInscription> inscriptions = new ArrayList<>();
        String query = "SELECT " +
                       "i.inscriptionId, " +
                       "i.etudiantId, " +
                       "i.moduleId, " +
                       "i.anneeUniversitaireId, " +
                       "e.matricule, " +
                       "e.name AS etudiantName, " +
                       "e.userName, " +
                       "e.email, " +
                       "e.telephone, " +
                       "e.dateNaissance, " +
                       "e.sexe, " +
                       "e.filiereId, " +
                       "f.name AS filiereName, " +
                       "au.debut AS anneeDebut, " +
                       "au.fin AS anneeFin, " +
                       "au.libelle AS anneeLibelle, " +
                       "m.moduleName " + // Supprimer m.moduleCode
                       "FROM inscriptions i " +
                       "LEFT JOIN etudiants e ON i.etudiantId = e.etudiantId " +
                       "LEFT JOIN filieres f ON e.filiereId = f.filiereId " +
                       "LEFT JOIN annees_universitaires au ON i.anneeUniversitaireId = au.anneeUniversitaireId " +
                       "LEFT JOIN modules m ON i.moduleId = m.moduleId " +
                       "ORDER BY i.inscriptionId";

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

                ModelModule module = null;
                if (r.getInt("moduleId") != 0) { // Vérifiez si un module est associé
                    module = new ModelModule(
                        r.getInt("moduleId"),
                        null, // Supprimer m.moduleCode
                        r.getString("moduleName"),
                        null, // Ajoutez d'autres champs si nécessaire
                        null
                    );
                }

                ModelInscription inscription = new ModelInscription(
                    r.getInt("inscriptionId"),
                    etudiant,
                    module, // Inclure le module
                    anneeUniversitaire,
                    r.getDate("dateInscription")
                );

                inscriptions.add(inscription);
            }
        }
        System.out.println("Inscriptions récupérées : " + inscriptions.size()); // Vérifiez le nombre d'inscriptions
        return inscriptions;
    }
    
    public void inscrireEtudiant(ModelInscription data) throws SQLException {
        // Vérifier si l'étudiant est déjà inscrit pour l'année universitaire donnée
        int etudiantId = getOrCreateEtudiantId(data.getEtudiant());

        // Insérer l'inscription dans la base de données
        PreparedStatement p = con.prepareStatement(
            "INSERT INTO inscriptions (etudiantId, anneeUniversitaireId) VALUES (?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        p.setInt(1, etudiantId);
        p.setInt(2, data.getAnneeUniversitaire().getAnneeUniversitaireId()); // Utiliser l'ID de l'année universitaire
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if (r.next()) {
            int inscriptionId = r.getInt(1);
            data.setInscriptionId(inscriptionId);
        }
        r.close();
        p.close();
    }

    public int getOrCreateEtudiantId(ModelEtudiant etudiant) throws SQLException {
        // Vérifier si l'étudiant existe déjà
        PreparedStatement checkStmt = con.prepareStatement(
            "SELECT etudiantId FROM etudiants WHERE matricule = ?"
        );
        checkStmt.setString(1, etudiant.getMatricule());
        ResultSet r = checkStmt.executeQuery();
        if (r.next()) {
            // Si l'étudiant existe, retourner son ID
            int etudiantId = r.getInt("etudiantId");
            r.close();
            checkStmt.close();
            return etudiantId;
        }
        r.close();
        checkStmt.close();

        // Si l'étudiant n'existe pas, le créer
        PreparedStatement insertStmt = con.prepareStatement(
            "INSERT INTO etudiants (matricule, name, userName, email, telephone, dateNaissance, sexe, anneeUniversitaireId, filiereId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        insertStmt.setString(1, etudiant.getMatricule());
        insertStmt.setString(2, etudiant.getName());
        insertStmt.setString(3, etudiant.getUserName());
        insertStmt.setString(4, etudiant.getEmail());
        insertStmt.setString(5, etudiant.getTelephone());
        insertStmt.setDate(6, new java.sql.Date(etudiant.getDateNaissance().getTime()));
        insertStmt.setString(7, etudiant.getSexe());
        insertStmt.setInt(8, etudiant.getAnneeUniversitaire().getAnneeUniversitaireId()); // Utiliser l'ID de l'année universitaire
        insertStmt.setInt(9, etudiant.getFiliere().getFiliereId());
        insertStmt.executeUpdate();
        ResultSet generatedKeys = insertStmt.getGeneratedKeys();
        int newEtudiantId = 0;
        if (generatedKeys.next()) {
            newEtudiantId = generatedKeys.getInt(1);
        }
        generatedKeys.close();
        insertStmt.close();
        return newEtudiantId;
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
}
