package com.gag.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectToDatabase();
            initializeDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeDatabase() {
        try (Statement stmt = connection.createStatement()) {
            // Créer la base de données si elle n'existe pas
            stmt.execute("CREATE DATABASE IF NOT EXISTS gestion_etudiant");
            stmt.execute("USE gestion_etudiant");

            // Créer la table users si elle n'existe pas
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "userID INT AUTO_INCREMENT PRIMARY KEY, " +
                "userName VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100) NOT NULL UNIQUE, " +
                "password VARCHAR(255) NOT NULL, " +
                "userType ENUM('admin', 'enseignant', 'etudiant') NOT NULL DEFAULT 'etudiant', " +
                "referenceID INT, " +  // ID lié à l'étudiant ou à l'enseignant permet de faire une difference entre l'étudiant ou l'enseignant
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
            ")";

            // Créer la table departements si elle n'existe pas
            String createDepartementsTable = "CREATE TABLE IF NOT EXISTS departements (" +
                "departementId INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL " +
            ")";

            // Créer la table enseignants si elle n'existe pas
            String createEnseignantsTable = "CREATE TABLE IF NOT EXISTS enseignants (" +
                "enseignantId INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "userName VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100) NOT NULL UNIQUE, " +
                "grade VARCHAR(50), " +
                "departementId INT, " +
                "FOREIGN KEY (departementId) REFERENCES departements(departementId)" +
            ")";

            // Créer la table filières si elle n'existe pas
            String createFilieresTable = "CREATE TABLE IF NOT EXISTS filieres (" +
                "filiereId INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "departementId INT, " +
                "FOREIGN KEY (departementId) REFERENCES departements(departementId)" +
            ")";

            // Créer la table etudiants si elle n'existe pas
            String createEtudiantsTable = "CREATE TABLE IF NOT EXISTS etudiants (" +
                "etudiantId INT AUTO_INCREMENT PRIMARY KEY, " +
                "matricule VARCHAR(100) NOT NULL, " +
                "name VARCHAR(100) NOT NULL, " +
                "userName VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100) NOT NULL UNIQUE, " +
                "telephone VARCHAR(15), " +
                "dateNaissance DATE, " +
                "sexe ENUM('M', 'F'), " +
                "anneeScolaire YEAR, " +
                "filiereId INT, " +
                "FOREIGN KEY (filiereId) REFERENCES filieres(filiereId)" +
            ")";

            // Créer la table semestres si elle n'existe pas
            String createSemestresTable = "CREATE TABLE IF NOT EXISTS semestres (" +
                "semestreId INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL " +
            ")";

            // Créer la table années universitaires si elle n'existe pas
            String createAnneesUniversitairesTable = "CREATE TABLE IF NOT EXISTS annees_universitaires (" +
                "anneeUniversitaireId INT AUTO_INCREMENT PRIMARY KEY, " +
                "debut YEAR NOT NULL, " +
                "fin YEAR NOT NULL, " +
                "libelle VARCHAR(50) NOT NULL" +
            ")";

            // Créer la table unités d’enseignement (UE) si elle n'existe pas
            String createUesTable = "CREATE TABLE IF NOT EXISTS ues (" +
                "ueId INT AUTO_INCREMENT PRIMARY KEY, " +
                "code VARCHAR(20) NOT NULL, " +
                "name VARCHAR(100) NOT NULL, " +
                "semestreId INT, " +
                "filiereId INT, " +
                "FOREIGN KEY (semestreId) REFERENCES semestres(semestreId)," +
                "FOREIGN KEY (filiereId) REFERENCES filieres(filiereId)" +
            ")";

            // Créer la table modules (matières) si elle n'existe pas
            String createModulesTable = "CREATE TABLE IF NOT EXISTS modules (" +
                "moduleId INT AUTO_INCREMENT PRIMARY KEY, " +
                "code VARCHAR(20) NOT NULL, " +
                "name VARCHAR(100) NOT NULL, " +
                "ueId INT, " +
                "enseignantId INT, " +
                "FOREIGN KEY (ueId) REFERENCES ues(ueId)," +
                "FOREIGN KEY (enseignantId) REFERENCES enseignants(enseignantId)" +
            ")";

            // Créer la table inscriptions si elle n'existe pas
            String createInscriptionsTable = "CREATE TABLE IF NOT EXISTS inscriptions (" +
                "inscriptionId INT AUTO_INCREMENT PRIMARY KEY, " +
                "etudiantId INT, " +
                "moduleId INT, " +
                "anneeUniversitaireId INT, " +
                "dateInscription DATE, " +
                "FOREIGN KEY (etudiantId) REFERENCES etudiants(etudiantId)," +
                "FOREIGN KEY (moduleId) REFERENCES modules(moduleId)," +
                "FOREIGN KEY (anneeUniversitaireId) REFERENCES annees_universitaires(anneeUniversitaireId)" +
            ")";

            // Créer la table notes si elle n'existe pas
            String createNotesTable = "CREATE TABLE IF NOT EXISTS notes (" +
                "noteId INT AUTO_INCREMENT PRIMARY KEY, " +
                "etudiantId INT, " +
                "moduleId INT, " +
                "anneeUniversitaireId INT, " +
                "note FLOAT, " +
                "FOREIGN KEY (etudiantId) REFERENCES inscriptions(etudiantId)," +
                "FOREIGN KEY (moduleId) REFERENCES modules(moduleId)," +
                "FOREIGN KEY (anneeUniversitaireId) REFERENCES annees_universitaires(anneeUniversitaireId)" +
            ")";

            stmt.execute(createUsersTable);
            stmt.execute(createDepartementsTable);
            stmt.execute(createEnseignantsTable);
            stmt.execute(createFilieresTable);
            stmt.execute(createEtudiantsTable);
            stmt.execute(createSemestresTable);
            stmt.execute(createAnneesUniversitairesTable);
            stmt.execute(createUesTable);
            stmt.execute(createModulesTable);
            stmt.execute(createInscriptionsTable);
            stmt.execute(createNotesTable);

            // Vérifier si l'admin existe, sinon le créer
            String checkAdmin = "SELECT COUNT(*) FROM users WHERE userType = 'admin'";
            var rs = stmt.executeQuery(checkAdmin);
            rs.next();
            if (rs.getInt(1) == 0) {
                String createAdmin = "INSERT INTO users (userName, email, password, userType) " +
                    "VALUES ('admin', 'admin@gag.com', 'admin123', 'admin')";
                stmt.execute(createAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void connectToDatabase() throws SQLException {
        String server = "localhost";
        String port = "3306";
        String database = "gestion_etudiant";
        String userName = "root";
        String password = "root";
        connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, userName, password);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
