package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelEmploiDuTemps;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceEmploiDuTemps {

    private final Connection con;

    public ServiceEmploiDuTemps() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void insertCours(ModelEmploiDuTemps cours) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "INSERT INTO emploi_du_temps (moduleId, enseignantId, jour, heureDebut, heureFin, salle) " +
            "VALUES (?, ?, ?, ?, ?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        p.setInt(1, cours.getModuleId());
        p.setInt(2, cours.getEnseignantId());
        p.setString(3, cours.getJour());
        p.setString(4, cours.getHeureDebut());
        p.setString(5, cours.getHeureFin());
        p.setString(6, cours.getSalle());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if (r.next()) {
            int coursId = r.getInt(1);
            cours.setCoursId(coursId);
        }
        r.close();
        p.close();
    }

    public void updateCours(ModelEmploiDuTemps cours) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "UPDATE emploi_du_temps SET moduleId = ?, enseignantId = ?, jour = ?, " +
            "heureDebut = ?, heureFin = ?, salle = ? WHERE coursId = ?"
        );
        p.setInt(1, cours.getModuleId());
        p.setInt(2, cours.getEnseignantId());
        p.setString(3, cours.getJour());
        p.setString(4, cours.getHeureDebut());
        p.setString(5, cours.getHeureFin());
        p.setString(6, cours.getSalle());
        p.setInt(7, cours.getCoursId());
        p.execute();
        p.close();
    }

    public void deleteCours(int coursId) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "DELETE FROM emploi_du_temps WHERE coursId = ?"
        );
        p.setInt(1, coursId);
        p.execute();
        p.close();
    }

    public List<ModelEmploiDuTemps> getEmploiDuTempsByModule(int moduleId) throws SQLException {
        List<ModelEmploiDuTemps> cours = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(
            "SELECT coursId, moduleId, enseignantId, jour, heureDebut, heureFin, salle " +
            "FROM emploi_du_temps WHERE moduleId = ?"
        );
        p.setInt(1, moduleId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            ModelEmploiDuTemps emploi = new ModelEmploiDuTemps(
                r.getInt("coursId"),
                r.getInt("moduleId"),
                r.getInt("enseignantId"),
                r.getString("jour"),
                r.getString("heureDebut"),
                r.getString("heureFin"),
                r.getString("salle")
            );
            cours.add(emploi);
        }
        r.close();
        p.close();
        return cours;
    }

    public List<ModelEmploiDuTemps> getEmploiDuTempsByEnseignant(int enseignantId) throws SQLException {
        List<ModelEmploiDuTemps> cours = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(
            "SELECT coursId, moduleId, enseignantId, jour, heureDebut, heureFin, salle " +
            "FROM emploi_du_temps WHERE enseignantId = ?"
        );
        p.setInt(1, enseignantId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            ModelEmploiDuTemps emploi = new ModelEmploiDuTemps(
                r.getInt("coursId"),
                r.getInt("moduleId"),
                r.getInt("enseignantId"),
                r.getString("jour"),
                r.getString("heureDebut"),
                r.getString("heureFin"),
                r.getString("salle")
            );
            cours.add(emploi);
        }
        r.close();
        p.close();
        return cours;
    }

    public List<ModelEmploiDuTemps> getEmploiDuTempsByEtudiant(int etudiantId) throws SQLException {
        List<ModelEmploiDuTemps> cours = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(
            "SELECT edt.coursId, edt.moduleId, edt.enseignantId, edt.jour, edt.heureDebut, edt.heureFin, edt.salle " +
            "FROM emploi_du_temps edt " +
            "JOIN inscriptions i ON edt.moduleId = i.moduleId " +
            "WHERE i.etudiantId = ?"
        );
        p.setInt(1, etudiantId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            ModelEmploiDuTemps emploi = new ModelEmploiDuTemps(
                r.getInt("coursId"),
                r.getInt("moduleId"),
                r.getInt("enseignantId"),
                r.getString("jour"),
                r.getString("heureDebut"),
                r.getString("heureFin"),
                r.getString("salle")
            );
            cours.add(emploi);
        }
        r.close();
        p.close();
        return cours;
    }

    public boolean checkConflitHoraire(int enseignantId, String jour, String heureDebut, String heureFin) throws SQLException {
        boolean conflit = false;
        PreparedStatement p = con.prepareStatement(
            "SELECT COUNT(*) FROM emploi_du_temps " +
            "WHERE enseignantId = ? AND jour = ? " +
            "AND ((heureDebut <= ? AND heureFin > ?) OR (heureDebut < ? AND heureFin >= ?))"
        );
        p.setInt(1, enseignantId);
        p.setString(2, jour);
        p.setString(3, heureFin);
        p.setString(4, heureDebut);
        p.setString(5, heureFin);
        p.setString(6, heureDebut);
        ResultSet r = p.executeQuery();
        if (r.next() && r.getInt(1) > 0) {
            conflit = true;
        }
        r.close();
        p.close();
        return conflit;
    }
} 