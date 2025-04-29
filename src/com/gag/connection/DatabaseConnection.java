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

            // Créer la table messages si elle n'existe pas
            String createMessagesTable = "CREATE TABLE IF NOT EXISTS messages (" +
                "messageId INT AUTO_INCREMENT PRIMARY KEY, " +
                "yourName VARCHAR(100) NOT NULL, " +
                "yourEmail VARCHAR(100) NOT NULL, " +
                "yourSubject VARCHAR(100) NOT NULL, " +
                "yourPhone VARCHAR(15) NOT NULL, " +
                "yourMessage TEXT NOT NULL, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
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

            // Créer la table etudiants si elle n'existe pas
            String createEtudiantsTable = "CREATE TABLE IF NOT EXISTS etudiants (" +
                "etudiantId INT AUTO_INCREMENT PRIMARY KEY, " +
                "matricule VARCHAR(100), " +
                "name VARCHAR(100) NOT NULL, " +
                "userName VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100) NOT NULL UNIQUE, " +
                "telephone VARCHAR(15), " +
                "dateNaissance DATE, " +
                "sexe ENUM('M', 'F'), " +
                "anneeUniversitaireId INT, " +
                "filiereId INT, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (anneeUniversitaireId) REFERENCES annees_universitaires(anneeUniversitaireId), " +
                "FOREIGN KEY (filiereId) REFERENCES filieres(filiereId)" +
            ")";

            // Créer la table unités d'enseignement (UE) si elle n'existe pas
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
                "FOREIGN KEY (ueId) REFERENCES ues(ueId) ON DELETE CASCADE," +
                "FOREIGN KEY (enseignantId) REFERENCES enseignants(enseignantId)" +
            ")";

            // Créer la table inscriptions si elle n'existe pas
            String createInscriptionsTable = "CREATE TABLE IF NOT EXISTS inscriptions (" +
                "inscriptionId INT AUTO_INCREMENT PRIMARY KEY, " +
                "etudiantId INT, " +
                "moduleId INT, " +
                "anneeUniversitaireId INT, " +
                "dateInscription TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
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
                "inscriptionId INT, " +
                "note FLOAT DEFAULT 0.0, " +
                "FOREIGN KEY (etudiantId) REFERENCES inscriptions(etudiantId)," +
                "FOREIGN KEY (moduleId) REFERENCES modules(moduleId)," +
                "FOREIGN KEY (anneeUniversitaireId) REFERENCES annees_universitaires(anneeUniversitaireId)," +
                "FOREIGN KEY (inscriptionId) REFERENCES inscriptions(inscriptionId)" +
            ")";

            stmt.execute(createUsersTable);
            stmt.execute(createMessagesTable);
            stmt.execute(createDepartementsTable);
            stmt.execute(createEnseignantsTable);
            stmt.execute(createFilieresTable);
            stmt.execute(createSemestresTable);
            stmt.execute(createAnneesUniversitairesTable);
            stmt.execute(createEtudiantsTable);
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

            // Vérifier s'il existe des semestres, sinon les créer
            String checkSemestres = "SELECT COUNT(*) FROM semestres";
            rs = stmt.executeQuery(checkSemestres);
            rs.next();
            if (rs.getInt(1) == 0) {
                String createSemestres = "INSERT INTO semestres (name) VALUES " +
                    "('Semestre 1'), ('Semestre 2'), ('Semestre 3'), ('Semestre 4'), ('Semestre 5'), ('Semestre 6')";
                stmt.execute(createSemestres);
            }

            // Vérifier s'il existe des années universitaires, sinon les créer
            String checkAnneesUniversitaires = "SELECT COUNT(*) FROM annees_universitaires";
            rs = stmt.executeQuery(checkAnneesUniversitaires);
            rs.next();
            if (rs.getInt(1) == 0) {
                String createAnneesUniversitaires = "INSERT INTO annees_universitaires (debut, fin, libelle) VALUES " +
                    "(2023, 2024, '2023-2024'), (2024, 2025, '2024-2025'), (2025, 2026, '2025-2026'), " +
                    "(2026, 2027, '2026-2027'), (2027, 2028, '2027-2028'), (2028, 2029, '2028-2029')";
                stmt.execute(createAnneesUniversitaires);
            }

            // Vérifier s'il existe des départements, sinon les créer
            String checkDepartements = "SELECT COUNT(*) FROM departements";
            rs = stmt.executeQuery(checkDepartements);
            rs.next();
            if (rs.getInt(1) == 0) {
                String createDepartements = "INSERT INTO departements (name) VALUES " +
                    "('Informatique'),('Mathématiques'),('Physique'),('Biologie')," +
                    "('Génie civil'),('Économie'),('Gestion'),('Droit')," +
                    "('Psychologie'),('Histoire'),('Sociologie'),('Littérature')," +
                    "('Langues étrangères'),('Médecine'),('Arts');";
                stmt.execute(createDepartements);
            }

            // Vérifier s'il existe des enseignants, sinon les créer
            String checkEnseignants = "SELECT COUNT(*) FROM enseignants";
            rs = stmt.executeQuery(checkEnseignants);
            rs.next();
            if (rs.getInt(1) == 0) {
                String createEnseignants = "INSERT INTO enseignants (name, userName, email, grade, departementId) VALUES " +
                    "('Dr. Jean Dupont', 'jdupont', 'jean.dupont@gag.com', 'Professeur', 1)," +
                    "('Dr. Marie Martin', 'mmartin', 'marie.martin@gag.com', 'Maître de conférences', 1)," +
                    "('Dr. Pierre Bernard', 'pbernard', 'pierre.bernard@gag.com', 'Assistant', 1)," +
                    "('Dr. Sophie Dubois', 'sdubois', 'sophie.dubois@gag.com', 'Professeur', 2)," +
                    "('Dr. Thomas Petit', 'tpetit', 'thomas.petit@gag.com', 'Maître de conférences', 2)," +
                    "('Dr. Claire Moreau', 'cmoreau', 'claire.moreau@gag.com', 'Professeur', 3)," +
                    "('Dr. Lucas Robert', 'lrobert', 'lucas.robert@gag.com', 'Assistant', 3)," +
                    "('Dr. Emma Richard', 'erichard', 'emma.richard@gag.com', 'Professeur', 4)," +
                    "('Dr. Hugo Simon', 'hsimon', 'hugo.simon@gag.com', 'Maître de conférences', 4)," +
                    "('Dr. Léa Michel', 'lmichel', 'lea.michel@gag.com', 'Professeur', 5)," +
                    "('Dr. Paul Leroy', 'pleroy', 'paul.leroy@gag.com', 'Assistant', 5)," +
                    "('Dr. Julie Roux', 'jroux', 'julie.roux@gag.com', 'Professeur', 6)," +
                    "('Dr. Antoine Girard', 'agirard', 'antoine.girard@gag.com', 'Maître de conférences', 6)," +
                    "('Dr. Camille David', 'cdavid', 'camille.david@gag.com', 'Professeur', 7)," +
                    "('Dr. Nicolas Bertrand', 'nbertrand', 'nicolas.bertrand@gag.com', 'Assistant', 7)," +
                    "('Dr. Sarah Lambert', 'slambert', 'sarah.lambert@gag.com', 'Professeur', 8)," +
                    "('Dr. Mathieu Bonnet', 'mbonnet', 'mathieu.bonnet@gag.com', 'Maître de conférences', 8)," +
                    "('Dr. Laura Fontaine', 'lfontaine', 'laura.fontaine@gag.com', 'Professeur', 9)," +
                    "('Dr. Alexandre Rousseau', 'arousseau', 'alexandre.rousseau@gag.com', 'Assistant', 9)," +
                    "('Dr. Chloé Vincent', 'cvincent', 'chloe.vincent@gag.com', 'Professeur', 10)," +
                    "('Dr. Gabriel Muller', 'gmuller', 'gabriel.muller@gag.com', 'Maître de conférences', 10)," +
                    "('Dr. Inès Durand', 'idurand', 'ines.durand@gag.com', 'Professeur', 11)," +
                    "('Dr. Louis Lefevre', 'llefevre', 'louis.lefevre@gag.com', 'Assistant', 11)," +
                    "('Dr. Alice Mercier', 'amercier', 'alice.mercier@gag.com', 'Professeur', 12)," +
                    "('Dr. Maxime Fournier', 'mfournier', 'maxime.fournier@gag.com', 'Maître de conférences', 12)," +
                    "('Dr. Eva Morel', 'emorel', 'eva.morel@gag.com', 'Professeur', 13)," +
                    "('Dr. Raphaël Andre', 'randre', 'raphael.andre@gag.com', 'Assistant', 13)," +
                    "('Dr. Louise Blanc', 'lblanc', 'louise.blanc@gag.com', 'Professeur', 14)," +
                    "('Dr. Arthur Henry', 'ahenry', 'arthur.henry@gag.com', 'Maître de conférences', 14)," +
                    "('Dr. Jade Roy', 'jroy', 'jade.roy@gag.com', 'Professeur', 15)," +
                    "('Dr. Théo Martinez', 'tmartinez', 'theo.martinez@gag.com', 'Assistant', 15);";
                stmt.execute(createEnseignants);
            }

            // Vérifier s'il existe des filières, sinon les créer
            String checkFilieres = "SELECT COUNT(*) FROM filieres";
            rs = stmt.executeQuery(checkFilieres);
            rs.next();
            if (rs.getInt(1) == 0) {
                String createFilieres = "INSERT INTO filieres (name, departementId) VALUES " +
                    "('Génie logiciel', 1),('Réseaux et télécoms', 1),('Gestion des technologies de l''information', 1),('Gestion des systèmes d''information', 1)," +
                    "('Mathématiques appliquées', 2),('Mathématiques pures', 2),('Mathématiques financières', 2),('Mathématiques statistiques', 2)," +
                    "('Physique fondamentale', 3),('Physique appliquée', 3),('Physique nucléaire', 3),('Physique des particules', 3)," +
                    "('Biotechnologie', 4),('Biologie moléculaire', 4),('Biologie cellulaire', 4),('Biologie générale', 4)," +
                    "('Génie civil', 5),('Génie hydraulique', 5),('Génie électrique', 5),('Génie mécanique', 5)," +
                    "('Sciences économiques', 6),('Economie politique', 6),('Economie mathématique', 6),('Economie générale', 6)," +
                    "('Management', 7),('Management des organisations', 7),('Management stratégique', 7),('Management financier', 7)," +
                    "('Droit des affaires', 8),('Droit commercial', 8),('Droit fiscal', 8),('Droit des assurances', 8)," +
                    "('Psychologie clinique', 9),('Psychologie cognitive', 9),('Psychologie sociale', 9),('Psychologie du développement', 9)," +
                    "('Histoire contemporaine', 10),('Histoire des sciences sociales', 10),('Histoire des religions', 10),('Histoire de l''art', 10)," +
                    "('Sociologie du travail', 11),('Sociologie de la famille', 11),('Sociologie urbaine', 11),('Sociologie des religions', 11)," +
                    "('Littérature comparée', 12),('Littérature française', 12),('Littérature anglaise', 12),('Littérature espagnole', 12)," +
                    "('Traduction et interprétation', 13),('Traduction automatique', 13),('Traduction terminologique', 13),('Traduction technique', 13)," +
                    "('Médecine générale', 14),('Médecine interne', 14),('Médecine chirurgicale', 14),('Médecine médicale', 14)," +
                    "('Arts plastiques', 15),('Musique', 15),('Théâtre', 15),('Cinéma', 15);";
                stmt.execute(createFilieres);
            }
            
            // Vérifier s'il existe des UEs, sinon les créer
            String checkUes = "SELECT COUNT(*) FROM ues";
            rs = stmt.executeQuery(checkUes);
            rs.next();
            if (rs.getInt(1) == 0) {
                String createUes = "INSERT INTO ues (code, name, semestreId, filiereId) VALUES " +
                    "('UE-GL1', 'Programmation Orientée Objet', 1, 1),('UE-GL2', 'Structures de Données', 2, 1),('UE-GL3', 'Base de Données', 3, 1),('UE-GL4', 'Systèmes d''exploitation', 4, 1)," +
                    "('UE-GL5', 'Réseaux et télécoms', 5, 1),('UE-GL6', 'Gestion des technologies de l''information', 6, 1),('UE-GL7', 'Gestion des systèmes d''information', 1, 1)," +
                    "('UE-RT1', 'Réseaux Informatiques', 1, 2),('UE-RT2', 'Télécommunications', 2, 2),('UE-RT3', 'Réseaux mobiles', 3, 2),('UE-RT4', 'Réseaux sans fil', 4, 2)," +
                    "('UE-MA1', 'Analyse Mathématique', 1, 5),('UE-MA2', 'Probabilités et Statistiques', 2, 5),('UE-MA3', 'Algèbre linéaire', 3, 5),('UE-MA4', 'Algèbre bilinéaire', 4, 5)," +
                    "('UE-PF1', 'Mécanique Classique', 1, 9),('UE-PF2', 'Optique', 2, 9),('UE-PF3', 'Mécanique des fluides', 3, 9),('UE-PF4', 'Mécanique des solides', 4, 9)," +
                    "('UE-BT1', 'Génétique', 1, 13),('UE-BT2', 'Microbiologie', 2, 13),('UE-BT3', 'Biologie moléculaire', 3, 13),('UE-BT4', 'Biologie cellulaire', 4, 13)," +
                    "('UE-GC1', 'Résistance des Matériaux', 1, 17),('UE-GC2', 'Béton Armé', 2, 17),('UE-GC3', 'Génie Civil', 3, 17),('UE-GC4', 'Génie Hydraulique', 4, 17)," +
                    "('UE-SE1', 'Macroéconomie', 1, 21),('UE-SE2', 'Microéconomie', 2, 21),('UE-SE3', 'Économie internationale', 3, 21),('UE-SE4', 'Économie du développement', 4, 21)," +
                    "('UE-MG1', 'Gestion des Entreprises', 1, 25),('UE-MG2', 'Comptabilité', 2, 25),('UE-MG3', 'Management stratégique', 3, 25),('UE-MG4', 'Management financier', 4, 25)," +
                    "('UE-DA1', 'Droit Civil', 1, 29),('UE-DA2', 'Droit Commercial', 2, 29),('UE-DA3', 'Droit Fiscal', 3, 29),('UE-DA4', 'Droit des assurances', 4, 29)," +
                    "('UE-PC1', 'Psychopathologie', 1, 33),('UE-PC2', 'Psychologie du Développement', 2, 33),('UE-PC3', 'Psychologie Sociale', 3, 33),('UE-PC4', 'Psychologie Clinique', 4, 33)," +
                    "('UE-HC1', 'Histoire Moderne', 1, 37),('UE-HC2', 'Histoire Contemporaine', 2, 37),('UE-HC3', 'Histoire des sciences sociales', 3, 37),('UE-HC4', 'Histoire des religions', 4, 37)," +
                    "('UE-ST1', 'Sociologie Générale', 1, 41),('UE-ST2', 'Sociologie du Travail', 2, 41),('UE-ST3', 'Sociologie urbaine', 3, 41),('UE-ST4', 'Sociologie des religions', 4, 41)," +
                    "('UE-LC1', 'Littérature Française', 1, 45),('UE-LC2', 'Littérature Comparée', 2, 45),('UE-LC3', 'Littérature Anglaise', 3, 45),('UE-LC4', 'Littérature Espagnole', 4, 45)," +
                    "('UE-TI1', 'Traduction Générale', 1, 49),('UE-TI2', 'Traduction Automatique', 2, 49),('UE-TI3', 'Traduction Terminologique', 3, 49),('UE-TI4', 'Traduction Technique', 4, 49)," +
                    "('UE-MD1', 'Médecine Générale', 1, 53),('UE-MD2', 'Médecine Interne', 2, 53),('UE-MD3', 'Médecine Chirurgicale', 3, 53),('UE-MD4', 'Médecine Médicale', 4, 53)," +
                    "('UE-AP1', 'Arts Plastiques', 1, 57),('UE-AP2', 'Histoire de l''art', 2, 57),('UE-AP3', 'Musique', 3, 57),('UE-AP4', 'Théâtre', 4, 57),('UE-AP5', 'Cinéma', 5, 57);";
                stmt.execute(createUes);
            }

            // Vérifier s'il existe des modules, sinon les créer
            String checkModules = "SELECT COUNT(*) FROM modules";
            rs = stmt.executeQuery(checkModules);
            rs.next();
            if (rs.getInt(1) == 0) {
                String createModules = "INSERT INTO modules (code, name, ueId, enseignantId) VALUES " +
                    // Modules pour Génie Logiciel (UE 1-4)
                    "('MOD-GL1', 'Java Programming', 1, 1)," +
                    "('MOD-GL2', 'Python Programming', 1, 2)," +
                    "('MOD-GL3', 'Algorithmes et Structures de Données', 2, 1)," +
                    "('MOD-GL4', 'Conception Orientée Objet', 2, 3)," +
                    "('MOD-GL5', 'SQL et NoSQL', 3, 2)," +
                    "('MOD-GL6', 'Conception de Bases de Données', 3, 1)," +
                    "('MOD-GL7', 'Linux et Shell Script', 4, 3)," +
                    "('MOD-GL8', 'Windows Server', 4, 2)," +

                    // Modules pour Mathématiques (UE 12-15)
                    "('MOD-MA1', 'Calcul Différentiel', 12, 4)," +
                    "('MOD-MA2', 'Intégration', 12, 5)," +
                    "('MOD-MA3', 'Statistiques Descriptives', 13, 4)," +
                    "('MOD-MA4', 'Probabilités', 13, 5)," +
                    "('MOD-MA5', 'Algèbre Linéaire', 14, 4)," +
                    "('MOD-MA6', 'Espaces Vectoriels', 14, 5)," +
                    "('MOD-MA7', 'Formes Bilinéaires', 15, 4)," +
                    "('MOD-MA8', 'Applications Linéaires', 15, 5)," +

                    // Modules pour Médecine
                    "('MOD-MED1', 'Anatomie', 53, 8)," +
                    "('MOD-MED2', 'Physiologie', 53, 9)," +
                    "('MOD-MED3', 'Pathologie', 54, 8)," +
                    "('MOD-MED4', 'Pharmacologie', 54, 9)," +
                    "('MOD-MED5', 'Chirurgie Générale', 54, 8)," +
                    "('MOD-MED6', 'Anesthésiologie', 54, 9)," +
                    "('MOD-MED7', 'Médecine Interne', 55, 8)," +
                    "('MOD-MED8', 'Urgences Médicales', 55, 9)," +

                    // Modules pour Arts
                    "('MOD-ART1', 'Dessin', 57, 10)," +
                    "('MOD-ART2', 'Peinture', 57, 11)," +
                    "('MOD-ART3', 'Histoire de l''Art Moderne', 58, 10)," +
                    "('MOD-ART4', 'Art Contemporain', 58, 11)," +
                    "('MOD-ART5', 'Théorie Musicale', 59, 10)," +
                    "('MOD-ART6', 'Pratique Instrumentale', 59, 11)," +
                    "('MOD-ART7', 'Art Dramatique', 60, 10)," +
                    "('MOD-ART8', 'Mise en Scène', 60, 11)," +
                    "('MOD-ART9', 'Réalisation', 61, 10)," +
                    "('MOD-ART10', 'Montage Vidéo', 61, 11);";
                stmt.execute(createModules);
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
        String password = "";
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
