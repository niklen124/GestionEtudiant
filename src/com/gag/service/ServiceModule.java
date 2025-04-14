package com.gag.service;

import com.gag.connection.DatabaseConnection;
import com.gag.model.ModelModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceModule {

    private final Connection con;

    public ServiceModule() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void insertModule(ModelModule module) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "INSERT INTO modules (code, name, ueId, enseignantId) VALUES (?, ?, ?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        p.setString(1, module.getCode());
        p.setString(2, module.getName());
        p.setInt(3, module.getUeId());
        p.setInt(4, module.getEnseignantId());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if (r.next()) {
            int moduleId = r.getInt(1);
            module.setModuleId(moduleId);
        }
        r.close();
        p.close();
    }

    public void updateModule(ModelModule module) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "UPDATE modules SET code = ?, name = ?, ueId = ?, enseignantId = ? WHERE moduleId = ?"
        );
        p.setString(1, module.getCode());
        p.setString(2, module.getName());
        p.setInt(3, module.getUeId());
        p.setInt(4, module.getEnseignantId());
        p.setInt(5, module.getModuleId());
        p.execute();
        p.close();
    }

    public void deleteModule(int moduleId) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "DELETE FROM modules WHERE moduleId = ?"
        );
        p.setInt(1, moduleId);
        p.execute();
        p.close();
    }

    public ModelModule getModuleById(int moduleId) throws SQLException {
        ModelModule module = null;
        PreparedStatement p = con.prepareStatement(
            "SELECT moduleId, code, name, ueId, enseignantId FROM modules WHERE moduleId = ?"
        );
        p.setInt(1, moduleId);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            module = new ModelModule(
                r.getInt("moduleId"),
                r.getString("code"),
                r.getString("name"),
                r.getInt("ueId"),
                r.getInt("enseignantId")
            );
        }
        r.close();
        p.close();
        return module;
    }

    public List<ModelModule> getAllModules() throws SQLException {
        List<ModelModule> modules = new ArrayList<>();
        String query = "SELECT moduleId, code, name, ueId, enseignantId FROM modules";
        
        try (PreparedStatement p = con.prepareStatement(query);
             ResultSet r = p.executeQuery()) {
            
            while (r.next()) {
                ModelModule module = new ModelModule(
                    r.getInt("moduleId"),
                    r.getString("code"),
                    r.getString("name"),
                    r.getInt("ueId"),
                    r.getInt("enseignantId")
                );
                modules.add(module);
            }
        }
        return modules;
    }

    public List<ModelModule> getModulesByUE(int ueId) throws SQLException {
        List<ModelModule> modules = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(
            "SELECT moduleId, code, name, ueId, enseignantId FROM modules WHERE ueId = ?"
        );
        p.setInt(1, ueId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            ModelModule module = new ModelModule(
                r.getInt("moduleId"),
                r.getString("code"),
                r.getString("name"),
                r.getInt("ueId"),
                r.getInt("enseignantId")
            );
            modules.add(module);
        }
        r.close();
        p.close();
        return modules;
    }

    public List<ModelModule> getModulesByEnseignant(int enseignantId) throws SQLException {
        List<ModelModule> modules = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(
            "SELECT moduleId, code, name, ueId, enseignantId FROM modules WHERE enseignantId = ?"
        );
        p.setInt(1, enseignantId);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            ModelModule module = new ModelModule(
                r.getInt("moduleId"),
                r.getString("code"),
                r.getString("name"),
                r.getInt("ueId"),
                r.getInt("enseignantId")
            );
            modules.add(module);
        }
        r.close();
        p.close();
        return modules;
    }

    public void assignEnseignant(int moduleId, int enseignantId) throws SQLException {
        PreparedStatement p = con.prepareStatement(
            "UPDATE modules SET enseignantId = ? WHERE moduleId = ?"
        );
        p.setInt(1, enseignantId);
        p.setInt(2, moduleId);
        p.execute();
        p.close();
    }
} 