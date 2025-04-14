package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelSemestre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}