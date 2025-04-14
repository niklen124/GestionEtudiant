# Documentation des Services

## ServiceUser
- `login()` : Authentification d'un utilisateur
- `insertUser()` : Création d'un nouvel utilisateur
- `updateUser()` : Mise à jour des informations d'un utilisateur
- `deleteUser()` : Suppression d'un utilisateur
- `checkDuplicateUser()` : Vérification de l'unicité du nom d'utilisateur
- `checkDuplicateEmail()` : Vérification de l'unicité de l'email
- `getAllUsers()` : Récupération de tous les utilisateurs
- `getUsersByType()` : Récupération des utilisateurs par type (admin, enseignant, étudiant)

## ServiceDepartement
- `insertDepartement()` : Création d'un nouveau département
- `updateDepartement()` : Mise à jour des informations d'un département
- `deleteDepartement()` : Suppression d'un département
- `getAllDepartements()` : Récupération de tous les départements
- `getDepartementById()` : Récupération d'un département par son ID

## ServiceFiliere
- `insertFiliere()` : Création d'une nouvelle filière
- `updateFiliere()` : Mise à jour des informations d'une filière
- `deleteFiliere()` : Suppression d'une filière
- `getAllFilieres()` : Récupération de toutes les filières
- `getFilieresByDepartement()` : Récupération des filières par département
- `getFiliereById()` : Récupération d'une filière par son ID

## ServiceAnneeUniversitaire
- `insertAnneeUniversitaire()` : Création d'une nouvelle année universitaire
- `updateAnneeUniversitaire()` : Mise à jour des informations d'une année universitaire
- `deleteAnneeUniversitaire()` : Suppression d'une année universitaire
- `getAllAnneesUniversitaires()` : Récupération de toutes les années universitaires
- `getAnneeUniversitaireById()` : Récupération d'une année universitaire par son ID
- `getAnneeUniversitaireActuelle()` : Récupération de l'année universitaire en cours

## ServiceUE
- `insertUE()` : Création d'une nouvelle UE
- `updateUE()` : Mise à jour des informations d'une UE
- `deleteUE()` : Suppression d'une UE
- `getAllUEs()` : Récupération de toutes les UE
- `getUEsByFiliere()` : Récupération des UE par filière
- `getUEsBySemestre()` : Récupération des UE par semestre
- `getUEById()` : Récupération d'une UE par son ID

## ServiceModule
- `insertModule()` : Création d'un nouveau module
- `updateModule()` : Mise à jour des informations d'un module
- `deleteModule()` : Suppression d'un module
- `getAllModules()` : Récupération de tous les modules
- `getModulesByUE()` : Récupération des modules par UE
- `getModulesByEnseignant()` : Récupération des modules par enseignant
- `getModuleById()` : Récupération d'un module par son ID
- `assignEnseignant()` : Assignation d'un enseignant à un module

## ServiceEmploiDuTemps
- `insertCours()` : Création d'un nouveau cours
- `updateCours()` : Mise à jour des informations d'un cours
- `deleteCours()` : Suppression d'un cours
- `getEmploiDuTempsByModule()` : Récupération de l'emploi du temps par module
- `getEmploiDuTempsByEnseignant()` : Récupération de l'emploi du temps par enseignant
- `getEmploiDuTempsByEtudiant()` : Récupération de l'emploi du temps par étudiant
- `checkConflitHoraire()` : Vérification des conflits d'horaires

## ServiceNote
- `insertNote()` : Saisie d'une nouvelle note
- `updateNote()` : Modification d'une note
- `deleteNote()` : Suppression d'une note
- `getNotesByEtudiant()` : Récupération des notes par étudiant
- `getNotesByModule()` : Récupération des notes par module
- `calculerMoyenneModule()` : Calcul de la moyenne d'un module
- `calculerMoyenneUE()` : Calcul de la moyenne d'une UE

## ServiceInscription
- `inscrireEtudiant()` : Inscription d'un étudiant à un module
- `desinscrireEtudiant()` : Désinscription d'un étudiant d'un module
- `getInscriptionsByEtudiant()` : Récupération des inscriptions par étudiant
- `getInscriptionsByModule()` : Récupération des inscriptions par module
- `verifierPreRequis()` : Vérification des prérequis pour l'inscription
- `getStatutInscription()` : Récupération du statut d'une inscription

## ServiceEnseignant
- `insertEnseignant()` : Création d'un nouvel enseignant
- `updateEnseignant()` : Mise à jour des informations d'un enseignant
- `deleteEnseignant()` : Suppression d'un enseignant
- `getAllEnseignants()` : Récupération de tous les enseignants
- `getEnseignantsByDepartement()` : Récupération des enseignants par département
- `getEnseignantById()` : Récupération d'un enseignant par son ID

## ServiceEtudiant
- `insertEtudiant()` : Création d'un nouvel étudiant
- `updateEtudiant()` : Mise à jour des informations d'un étudiant
- `deleteEtudiant()` : Suppression d'un étudiant
- `getAllEtudiants()` : Récupération de tous les étudiants
- `getEtudiantsByFiliere()` : Récupération des étudiants par filière
- `getEtudiantById()` : Récupération d'un étudiant par son ID
- `getEtudiantByMatricule()` : Récupération d'un étudiant par son matricule 