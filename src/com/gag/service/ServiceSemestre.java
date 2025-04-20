package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelSemestre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceSemestre {

    private final Connection con;

    public ServiceSemestre() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public ModelSemestre getSemestreById(int semestreId) throws SQLException {
        ModelSemestre semestre = null;
        PreparedStatement p = con.prepareStatement(
            "SELECT semestreId, name FROM semestres WHERE semestreId = ?"
        );
        p.setInt(1, semestreId);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            semestre = new ModelSemestre(
                r.getInt("semestreId"),
                r.getString("name")
            );
        }
        r.close();
        p.close();
        return semestre;
    }

    // Nouvelle méthode pour récupérer tous les semestres
    public List<ModelSemestre> getAllSemestres() throws SQLException {
        List<ModelSemestre> semestres = new ArrayList<>();
        String query = "SELECT semestreId, name FROM semestres ORDER BY name";

        try (PreparedStatement p = con.prepareStatement(query);
             ResultSet r = p.executeQuery()) {

            while (r.next()) {
                ModelSemestre semestre = new ModelSemestre(
                    r.getInt("semestreId"),
                    r.getString("name")
                );
                semestres.add(semestre);
            }
        }
        return semestres;
    }
}