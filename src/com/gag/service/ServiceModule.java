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
} 