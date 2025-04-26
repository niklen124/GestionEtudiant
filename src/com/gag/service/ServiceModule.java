package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelDepartement;
import com.gag.model.ModelEnseignant;
import com.gag.model.ModelFiliere;
import com.gag.model.ModelModule;
import com.gag.model.ModelSemestre;
import com.gag.model.ModelUE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceModule {

    private ServiceDepartement serviceDepartement;
    private ServiceFiliere serviceFiliere;
    private ServiceSemestre serviceSemestre;
    private final Connection con;

    public ServiceModule() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public List<ModelModule> getAllModules() throws SQLException {
        List<ModelModule> modules = new ArrayList<>();
        String query = "SELECT modules.moduleId, modules.code AS moduleCode, modules.name AS moduleName, " +
                   "ues.ueId, ues.code AS ueCode, ues.name AS ueName, " +
                   "filieres.filiereId, filieres.name AS filiereName, " +
                   "semestres.semestreId, semestres.name AS semestreName, " +
                   "enseignants.enseignantId, enseignants.name AS enseignantName, enseignants.userName, enseignants.email, enseignants.grade, " +
                   "departements.departementId, departements.name AS departementName " +
                   "FROM modules " +
                   "JOIN ues ON modules.ueId = ues.ueId " +
                   "JOIN filieres ON ues.filiereId = filieres.filiereId " +
                   "JOIN semestres ON ues.semestreId = semestres.semestreId " +
                   "JOIN enseignants ON modules.enseignantId = enseignants.enseignantId " +
                   "JOIN departements ON filieres.departementId = departements.departementId " +
                   "ORDER BY modules.name";
        
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

                ModelEnseignant enseignant = new ModelEnseignant(
                    r.getInt("enseignantId"),
                    r.getString("enseignantName"),
                    r.getString("userName"),
                    r.getString("email"),
                    r.getString("grade"),
                    departement
                );

                ModelModule module = new ModelModule(
                    r.getInt("moduleId"),
                    r.getString("moduleCode"),
                    r.getString("moduleName"),
                    ue,
                    enseignant
                );
                modules.add(module);
            }
        }
        return modules;
    }

    public List<ModelModule> getAllModulesByFiliere(int filiereId) throws SQLException {
        List<ModelModule> modules = new ArrayList<>();
        String query = "SELECT modules.moduleId, modules.code AS moduleCode, modules.name AS moduleName, " +
                       "ues.ueId, ues.code AS ueCode, ues.name AS ueName, " +
                       "filieres.filiereId, filieres.name AS filiereName, " +
                       "semestres.semestreId, semestres.name AS semestreName, " +
                       "enseignants.enseignantId, enseignants.name AS enseignantName, enseignants.userName, enseignants.email, enseignants.grade, " +
                       "departements.departementId, departements.name AS departementName " +
                       "FROM modules " +
                       "JOIN ues ON modules.ueId = ues.ueId " +
                       "JOIN filieres ON ues.filiereId = filieres.filiereId " +
                       "JOIN semestres ON ues.semestreId = semestres.semestreId " +
                       "JOIN enseignants ON modules.enseignantId = enseignants.enseignantId " +
                       "JOIN departements ON filieres.departementId = departements.departementId " +
                       "WHERE filieres.filiereId = ? " +
                       "ORDER BY modules.name";

        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setInt(1, filiereId); // Filtrer par l'ID de la filière
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

                    ModelEnseignant enseignant = new ModelEnseignant(
                        r.getInt("enseignantId"),
                        r.getString("enseignantName"),
                        r.getString("userName"),
                        r.getString("email"),
                        r.getString("grade"),
                        departement
                    );

                    ModelModule module = new ModelModule(
                        r.getInt("moduleId"),
                        r.getString("moduleCode"),
                        r.getString("moduleName"),
                        ue,
                        enseignant
                    );
                    modules.add(module);
                }
            }
        }
        return modules;
    }

    public ModelModule getModuleById(int moduleId) throws SQLException {
        String query = "SELECT modules.moduleId, modules.code AS moduleCode, modules.name AS moduleName, " +
                       "ues.ueId, ues.code AS ueCode, ues.name AS ueName, " +
                       "filieres.filiereId, filieres.name AS filiereName, " +
                       "semestres.semestreId, semestres.name AS semestreName, " +
                       "enseignants.enseignantId, enseignants.name AS enseignantName, enseignants.userName, enseignants.email, enseignants.grade, " +
                       "departements.departementId, departements.name AS departementName " +
                       "FROM modules " +
                       "JOIN ues ON modules.ueId = ues.ueId " +
                       "JOIN filieres ON ues.filiereId = filieres.filiereId " +
                       "JOIN semestres ON ues.semestreId = semestres.semestreId " +
                       "JOIN enseignants ON modules.enseignantId = enseignants.enseignantId " +
                       "JOIN departements ON filieres.departementId = departements.departementId " +
                       "WHERE modules.moduleId = ?";

        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setInt(1, moduleId); // Filtrer par l'ID du module
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
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

                    ModelEnseignant enseignant = new ModelEnseignant(
                        r.getInt("enseignantId"),
                        r.getString("enseignantName"),
                        r.getString("userName"),
                        r.getString("email"),
                        r.getString("grade"),
                        departement
                    );

                    return new ModelModule(
                        r.getInt("moduleId"),
                        r.getString("moduleCode"),
                        r.getString("moduleName"),
                        ue,
                        enseignant
                    );
                }
            }
        }
        return null; // Retourne null si aucun module n'est trouvé
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

    public boolean verifierEnseignantExiste(String nomEnseignant) throws SQLException {
        String query = "SELECT COUNT(*) FROM enseignants WHERE name = ?";
        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setString(1, nomEnseignant);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                return r.getInt(1) > 0;
            }
            return false;
        }
    }

    public void insertModule(ModelModule module) throws SQLException {
        // D'abord insérer l'UE
        int ueId = insertUE(module.getUe());
        
        // Ensuite insérer le module avec l'ID de l'UE
        String query = "INSERT INTO modules (code, name, ueId, enseignantId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setString(1, module.getCode());
            p.setString(2, module.getName());
            p.setInt(3, ueId);
            p.setInt(4, module.getEnseignant().getEnseignantId());
            p.executeUpdate();
        }
    }
    
    private int insertUE(ModelUE ue) throws SQLException {
        String query = "INSERT INTO ues (code, name, semestreId, filiereId) VALUES (?, ?, ?, ?)";
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
            throw new SQLException("Impossible de récupérer l'ID de l'UE créée");
        }
    }

    public void deleteModule(int moduleId) throws SQLException {
        // Supprimer directement le module
        String queryDeleteModule = "DELETE FROM modules WHERE moduleId = ?";
        try (PreparedStatement p = con.prepareStatement(queryDeleteModule)) {
            p.setInt(1, moduleId);
            int rowsAffected = p.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Aucun module trouvé avec l'ID : " + moduleId);
            }
        }
    }

    public void editModule(ModelModule module) throws SQLException {
        // Mettre à jour l'UE associée
        String queryUpdateUE = "UPDATE ues SET code = ?, name = ?, semestreId = ?, filiereId = ? WHERE ueId = ?";
        try (PreparedStatement p = con.prepareStatement(queryUpdateUE)) {
            p.setString(1, module.getUe().getCode());
            p.setString(2, module.getUe().getName());
            p.setInt(3, module.getUe().getSemestre().getSemestreId());
            p.setInt(4, module.getUe().getFiliere().getFiliereId());
            p.setInt(5, module.getUe().getUeId());
            p.executeUpdate();
        }

        // Mettre à jour le module
        String queryUpdateModule = "UPDATE modules SET code = ?, name = ?, enseignantId = ? WHERE moduleId = ?";
        try (PreparedStatement p = con.prepareStatement(queryUpdateModule)) {
            p.setString(1, module.getCode());
            p.setString(2, module.getName());
            p.setInt(3, module.getEnseignant().getEnseignantId());
            p.setInt(4, module.getModuleId());
            p.executeUpdate();
        }
    }
}