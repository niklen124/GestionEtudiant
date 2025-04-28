package com.gag.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelAnneeUniversitaire;
import com.gag.model.ModelDepartement;
import com.gag.model.ModelEnseignant;
import com.gag.model.ModelEtudiant;
import com.gag.model.ModelFiliere;
import com.gag.model.ModelNote;
import com.gag.model.ModelSemestre;
import com.gag.model.ModelUE;
import com.gag.model.ModelModule;
import com.gag.model.ModelInscription;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiceSaisirNotes {

    private ServiceDepartement serviceDepartement;
    private ServiceFiliere serviceFiliere;
    private ServiceSemestre serviceSemestre;
    private ServiceAnneeUniversitaire serviceAnneeUniversitaire;
    private Connection con;

    public ServiceSaisirNotes() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public List<ModelNote> getAllNotes() throws SQLException {
        String query = "SELECT n.noteId, n.note, n.etudiantId, n.moduleId, n.anneeUniversitaireId, n.inscriptionId, " +
                       "e.matricule, e.name AS etudiantName, e.userName, e.email, e.telephone, e.dateNaissance, e.sexe, " +
                       "m.name AS moduleName, m.code AS moduleCode, " +
                       "a.debut AS anneeDebut, a.fin AS anneeFin, a.libelle AS anneeLibelle, " +
                       "s.semestreId, s.name AS semestreName, " +
                       "d.departementId, d.name AS departementName, " +
                       "f.filiereId, f.name AS filiereName, " +
                       "u.ueId, u.code AS ueCode, u.name AS ueName, " +
                       "en.enseignantId, en.name AS enseignantName, en.userName, en.email, en.grade " +
                       "FROM notes n " +
                       "JOIN etudiants e ON n.etudiantId = e.etudiantId " +
                       "JOIN modules m ON n.moduleId = m.moduleId " +
                       "JOIN annees_universitaires a ON n.anneeUniversitaireId = a.anneeUniversitaireId " +
                       "JOIN ues u ON m.ueId = u.ueId " +
                       "JOIN filieres f ON u.filiereId = f.filiereId " +
                       "JOIN departements d ON f.departementId = d.departementId " +
                       "JOIN semestres s ON u.semestreId = s.semestreId " +
                       "JOIN enseignants en ON m.enseignantId = en.enseignantId";

        List<ModelNote> notes = new ArrayList<>();

        try (PreparedStatement p = con.prepareStatement(query);
             ResultSet r = p.executeQuery()) {
            while (r.next()) {
                ModelSemestre semestre = new ModelSemestre(
                        r.getInt("semestreId"),
                        r.getString("semestreName")
                );

                ModelDepartement departement = new ModelDepartement(
                    r.getInt("departementId"),
                    r.getString("departementName")
                );

                ModelFiliere filiere = new ModelFiliere(
                    r.getInt("filiereId"),
                    r.getString("filiereName"),
                    departement
                );

                ModelUE ue = new ModelUE(
                    r.getInt("ueId"),
                    r.getString("ueCode"),
                    r.getString("ueName"),
                    semestre,
                    filiere
                );

                // Construire l'objet ModelAnneeUniversitaire
                ModelAnneeUniversitaire anneeUniversitaire = new ModelAnneeUniversitaire(
                    r.getInt("anneeUniversitaireId"),
                    r.getInt("anneeDebut"),
                    r.getInt("anneeFin"),
                    r.getString("anneeLibelle")
                );

                ModelEnseignant enseignant = new ModelEnseignant(
                        r.getInt("enseignantId"),
                        r.getString("enseignantName"),
                        r.getString("userName"),
                        r.getString("email"),
                        r.getString("grade"),
                        departement
                    );

                // Construire l'objet ModelModule
                ModelModule module = new ModelModule(
                    r.getInt("moduleId"),
                    r.getString("moduleCode"),
                    r.getString("moduleName"),
                    ue,
                    enseignant
                );

                // Construire l'objet ModelEtudiant
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

                // Construire l'objet ModelInscription
                ModelInscription inscription = new ModelInscription();
                inscription.setInscriptionId(r.getInt("inscriptionId"));

                // Construire l'objet ModelNote
                ModelNote note = new ModelNote(
                    r.getInt("noteId"),
                    r.getDouble("note"),
                    etudiant,
                    module,
                    anneeUniversitaire,
                    inscription // Associer l'inscription à la note
                );

                // Ajouter la note à la liste
                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    public List<ModelNote> searchNotes(String search) throws SQLException {
        List<ModelNote> notes = new ArrayList<>();
        String query = "SELECT n.noteId, n.note, n.etudiantId, n.moduleId, n.anneeUniversitaireId, n.inscriptionId, " +
                       "e.matricule, e.name AS etudiantName, e.userName, e.email, e.telephone, e.dateNaissance, e.sexe, " +
                       "m.name AS moduleName, m.code AS moduleCode, " +
                       "a.debut AS anneeDebut, a.fin AS anneeFin, a.libelle AS anneeLibelle, " +
                       "s.semestreId, s.name AS semestreName, " +
                       "d.departementId, d.name AS departementName, " +
                       "f.filiereId, f.name AS filiereName, " +
                       "u.ueId, u.code AS ueCode, u.name AS ueName " +
                       "FROM notes n " +
                       "JOIN etudiants e ON n.etudiantId = e.etudiantId " +
                       "JOIN modules m ON n.moduleId = m.moduleId " +
                       "JOIN annees_universitaires a ON n.anneeUniversitaireId = a.anneeUniversitaireId " +
                       "JOIN ues u ON m.ueId = u.ueId " +
                       "JOIN filieres f ON u.filiereId = f.filiereId " +
                       "JOIN departements d ON f.departementId = d.departementId " +
                       "JOIN semestres s ON u.semestreId = s.semestreId " +
                       "WHERE (e.name LIKE ? OR e.email LIKE ? OR m.name LIKE ? OR u.name LIKE ? OR f.name LIKE ? OR d.name LIKE ?) " +
                       "ORDER BY e.name";

        try (PreparedStatement p = con.prepareStatement(query)) {
            // Définir les paramètres pour la recherche
            String searchPattern = "%" + search + "%";
            p.setString(1, searchPattern);
            p.setString(2, searchPattern);
            p.setString(3, searchPattern);
            p.setString(4, searchPattern);
            p.setString(5, searchPattern);
            p.setString(6, searchPattern);

            // Exécuter la requête
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    // Construire l'objet ModelSemestre
                    ModelSemestre semestre = new ModelSemestre(
                        r.getInt("semestreId"),
                        r.getString("semestreName")
                    );

                    // Construire l'objet ModelDepartement
                    ModelDepartement departement = new ModelDepartement(
                        r.getInt("departementId"),
                        r.getString("departementName")
                    );

                    // Construire l'objet ModelFiliere
                    ModelFiliere filiere = new ModelFiliere(
                        r.getInt("filiereId"),
                        r.getString("filiereName"),
                        departement
                    );

                    // Construire l'objet ModelUE
                    ModelUE ue = new ModelUE(
                        r.getInt("ueId"),
                        r.getString("ueCode"),
                        r.getString("ueName"),
                        semestre,
                        filiere
                    );

                    // Construire l'objet ModelAnneeUniversitaire
                    ModelAnneeUniversitaire anneeUniversitaire = new ModelAnneeUniversitaire(
                        r.getInt("anneeUniversitaireId"),
                        r.getInt("anneeDebut"),
                        r.getInt("anneeFin"),
                        r.getString("anneeLibelle")
                    );

                    // Construire l'objet ModelModule
                    ModelModule module = new ModelModule(
                        r.getInt("moduleId"),
                        r.getString("moduleCode"),
                        r.getString("moduleName"),
                        ue,
                        null
                    );

                    // Construire l'objet ModelEtudiant
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

                    // Construire l'objet ModelInscription
                    ModelInscription inscription = new ModelInscription();
                    inscription.setInscriptionId(r.getInt("inscriptionId"));

                    // Construire l'objet ModelNote
                    ModelNote note = new ModelNote(
                        r.getInt("noteId"),
                        r.getDouble("note"),
                        etudiant,
                        module,
                        anneeUniversitaire,
                        inscription // Associer l'inscription à la note
                    );

                    // Ajouter la note à la liste
                    notes.add(note);
                }
            }
        }

        return notes;
    }

    public List<ModelNote> getNotesByEtudiantId(int etudiantId) throws SQLException {
        List<ModelNote> notes = new ArrayList<>();
        String query = "SELECT n.noteId, n.note, n.etudiantId, n.moduleId, n.anneeUniversitaireId, n.inscriptionId, " +
                       "e.matricule, e.name AS etudiantName, e.userName, e.email, e.telephone, e.dateNaissance, e.sexe, " +
                       "m.name AS moduleName, m.code AS moduleCode, " +
                       "a.debut AS anneeDebut, a.fin AS anneeFin, a.libelle AS anneeLibelle, " +
                       "s.semestreId, s.name AS semestreName, " +
                       "d.departementId, d.name AS departementName, " +
                       "f.filiereId, f.name AS filiereName, " +
                       "u.ueId, u.code AS ueCode, u.name AS ueName " +
                       "FROM notes n " +
                       "JOIN etudiants e ON n.etudiantId = e.etudiantId " +
                       "JOIN modules m ON n.moduleId = m.moduleId " +
                       "JOIN annees_universitaires a ON n.anneeUniversitaireId = a.anneeUniversitaireId " +
                       "JOIN ues u ON m.ueId = u.ueId " +
                       "JOIN filieres f ON u.filiereId = f.filiereId " +
                       "JOIN departements d ON f.departementId = d.departementId " +
                       "JOIN semestres s ON u.semestreId = s.semestreId " +
                       "WHERE n.etudiantId = ?";

        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setInt(1, etudiantId); // Filtrer par l'ID de l'étudiant connecté
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    ModelSemestre semestre = new ModelSemestre(
                        r.getInt("semestreId"),
                        r.getString("semestreName")
                    );

                    ModelDepartement departement = new ModelDepartement(
                        r.getInt("departementId"),
                        r.getString("departementName")
                    );

                    ModelFiliere filiere = new ModelFiliere(
                        r.getInt("filiereId"),
                        r.getString("filiereName"),
                        departement
                    );

                    ModelUE ue = new ModelUE(
                        r.getInt("ueId"),
                        r.getString("ueCode"),
                        r.getString("ueName"),
                        semestre,
                        filiere
                    );

                    ModelAnneeUniversitaire anneeUniversitaire = new ModelAnneeUniversitaire(
                        r.getInt("anneeUniversitaireId"),
                        r.getInt("anneeDebut"),
                        r.getInt("anneeFin"),
                        r.getString("anneeLibelle")
                    );

                    ModelModule module = new ModelModule(
                        r.getInt("moduleId"),
                        r.getString("moduleCode"),
                        r.getString("moduleName"),
                        ue,
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

                    ModelInscription inscription = new ModelInscription();
                    inscription.setInscriptionId(r.getInt("inscriptionId"));

                    ModelNote note = new ModelNote(
                        r.getInt("noteId"),
                        r.getDouble("note"),
                        etudiant,
                        module,
                        anneeUniversitaire,
                        inscription
                    );

                    notes.add(note);
                }
            }
        }

        return notes;
    }

    public void insertNote(ModelNote note) throws SQLException {
        // D'abord insérer l'UE si elle n'existe pas
        int ueId = insertUE(note.getModule().getUe());

        // Ensuite insérer le module avec l'ID de l'UE
        int moduleId = insertModule(note.getModule(), ueId);

        // Insérer l'inscription si elle n'existe pas
        int inscriptionId = insertInscription(note.getEtudiant().getEtudiantId(), moduleId, note.getAnneeUniversitaire().getAnneeUniversitaireId());

        // Enfin, insérer la note avec l'ID de l'inscription
        String query = "INSERT INTO notes (etudiantId, moduleId, anneeUniversitaireId, inscriptionId, note) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setInt(1, note.getEtudiant().getEtudiantId());
            p.setInt(2, moduleId);
            p.setInt(3, note.getAnneeUniversitaire().getAnneeUniversitaireId());
            p.setInt(4, inscriptionId);
            p.setDouble(5, note.getNote());
            p.executeUpdate();
        }
    }

    private int insertUE(ModelUE ue) throws SQLException {
        String query = "INSERT INTO ues (code, name, semestreId, filiereId) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE ueId=LAST_INSERT_ID(ueId)";
        try (PreparedStatement p = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            p.setString(1, ue.getCode());
            p.setString(2, ue.getName());
            p.setInt(3, ue.getSemestre().getSemestreId());
            p.setInt(4, ue.getFiliere().getFiliereId());
            p.executeUpdate();

            ResultSet r = p.getGeneratedKeys();
            if (r.next()) {
                return r.getInt(1);
            }
            throw new SQLException("Impossible de récupérer l'ID de l'UE créée ou existante");
        }
    }

    private int insertModule(ModelModule module, int ueId) throws SQLException {
        String query = "INSERT INTO modules (code, name, ueId, enseignantId) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE moduleId=LAST_INSERT_ID(moduleId)";
        try (PreparedStatement p = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            p.setString(1, module.getCode());
            p.setString(2, module.getName());
            p.setInt(3, ueId);

            if (module.getEnseignant() != null) {
                p.setInt(4, module.getEnseignant().getEnseignantId());
            } else {
                p.setNull(4, java.sql.Types.INTEGER);
            }

            p.executeUpdate();

            ResultSet r = p.getGeneratedKeys();
            if (r.next()) {
                return r.getInt(1);
            }
            throw new SQLException("Impossible de récupérer l'ID du module créé ou existant");
        }
    }

    private int insertInscription(int etudiantId, int moduleId, int anneeUniversitaireId) throws SQLException {
        String query = "INSERT INTO inscriptions (etudiantId, moduleId, anneeUniversitaireId) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE inscriptionId=LAST_INSERT_ID(inscriptionId)";
        try (PreparedStatement p = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            p.setInt(1, etudiantId);
            p.setInt(2, moduleId);
            p.setInt(3, anneeUniversitaireId);
            p.executeUpdate();

            ResultSet r = p.getGeneratedKeys();
            if (r.next()) {
                return r.getInt(1);
            }
            throw new SQLException("Impossible de récupérer l'ID de l'inscription créée ou existante");
        }
    }

    public void deleteNote(int noteId) throws SQLException {
        // Supprimer directement la note
        String queryDeleteNote = "DELETE FROM notes WHERE noteId = ?";
        try (PreparedStatement p = con.prepareStatement(queryDeleteNote)) {
            p.setInt(1, noteId);
            int rowsAffected = p.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Aucune note trouvée avec l'ID : " + noteId);
            }
        }
    }

    public void editNote(ModelNote note) throws SQLException {
        // Mettre à jour l'inscription associée
        String queryUpdateInscription = "UPDATE inscriptions SET etudiantId = ?, moduleId = ?, anneeUniversitaireId = ? WHERE inscriptionId = ?";
        try (PreparedStatement p = con.prepareStatement(queryUpdateInscription)) {
            p.setInt(1, note.getEtudiant().getEtudiantId());
            p.setInt(2, note.getModule().getModuleId());
            p.setInt(3, note.getAnneeUniversitaire().getAnneeUniversitaireId());
            p.setInt(4, note.getInscriptionId()); // Assurez-vous que l'objet `ModelNote` contient l'ID de l'inscription
            p.executeUpdate();
        }

        // Mettre à jour la note
        String queryUpdateNote = "UPDATE notes SET note = ?, etudiantId = ?, moduleId = ?, anneeUniversitaireId = ? WHERE noteId = ?";
        try (PreparedStatement p = con.prepareStatement(queryUpdateNote)) {
            p.setDouble(1, note.getNote());
            p.setInt(2, note.getEtudiant().getEtudiantId());
            p.setInt(3, note.getModule().getModuleId());
            p.setInt(4, note.getAnneeUniversitaire().getAnneeUniversitaireId());
            p.setInt(5, note.getNoteId());
            p.executeUpdate();
        }
    }

    public ServiceDepartement getServiceDepartement() {
        if (serviceDepartement == null) { 
            serviceDepartement = new ServiceDepartement();
        }
        return serviceDepartement;
    }

    public ServiceFiliere getServiceFiliere() {
        if (serviceFiliere == null) {
            serviceFiliere = new ServiceFiliere();
        }
        return serviceFiliere;
    }

    public ServiceSemestre getServiceSemestre() {
        if (serviceSemestre == null) {
            serviceSemestre = new ServiceSemestre();
        }
        return serviceSemestre;
    }

    public ServiceAnneeUniversitaire getServiceAnneeUniversitaire() {
        if (serviceAnneeUniversitaire == null) {
            serviceAnneeUniversitaire = new ServiceAnneeUniversitaire();
        }
        return serviceAnneeUniversitaire;
    }
}
