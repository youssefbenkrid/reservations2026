# Projet Réservations (Java / Spring Boot 4)

Application web de réservation de spectacles, développée en Java avec Spring Boot 4, Spring Security et MariaDB.

Projet de cours du bachelier en informatique, orientation développement d'applications, à l'Institut des Carrières Commerciales (ICC, Bruxelles). Le projet est en cours de développement : cette première partie pose les bases de l'application, avec les comptes utilisateurs, la sécurité et la gestion des artistes.

## Fonctionnalités

**Comptes utilisateurs**
- Inscription avec validation des champs : login de 4 à 20 caractères, e-mail valide, langue (français, anglais ou néerlandais) et mot de passe fort (8 caractères minimum, avec majuscule, minuscule, chiffre et caractère spécial), saisi deux fois
- Connexion et déconnexion, avec un message en cas d'échec, de déconnexion ou d'accès à une page protégée
- Profil : modification de ses informations et de son mot de passe (le login et le rôle ne sont pas modifiables), suppression de son compte
- Mot de passe oublié : lien de réinitialisation envoyé par e-mail, valable une heure. Une tâche planifiée supprime les liens expirés toutes les heures.
- Rôles : administrateur, membre, affilié, critique de presse et producteur. Un nouvel inscrit devient membre.

**Artistes** (réservé aux utilisateurs connectés)
- Liste, fiche, ajout, modification et suppression, avec validation des champs

## Technologies

| Domaine | Outils |
| --- | --- |
| Langage et build | Java 21, Maven |
| Framework | Spring Boot 4.1.1 (Spring MVC) |
| Vues | Thymeleaf, Thymeleaf Layout Dialect, Thymeleaf Extras Spring Security |
| Données | Spring Data JPA (Hibernate), MariaDB, migrations Flyway |
| Sécurité | Spring Security : mots de passe hachés avec BCrypt, protection CSRF, pages réservées aux utilisateurs connectés |
| Validation | Bean Validation et annotations personnalisées (`@StrongPassword`, `@PasswordMatches`) |
| E-mails | Spring Mail, avec Mailtrap en développement |
| Divers | Lombok, tâches planifiées (`@Scheduled`) |

## Organisation du code

```
src/main/java/be/iccbxl/pid/reservations2026/
  config/       Sécurité (Spring Security) et configuration de Thymeleaf
  controller/   Accueil, connexion, inscription, profil, artistes
  dto/          Données des formulaires d'inscription et de profil
  model/        Entités JPA : utilisateur, artiste, jeton de réinitialisation
  repository/   Accès aux données (Spring Data JPA)
  service/      Logique métier, envoi d'e-mails, nettoyage des jetons expirés
  validation/   Règles de validation personnalisées
src/main/resources/
  db/migration/ Migrations Flyway (tables et données de départ)
  templates/    Vues Thymeleaf (gabarit commun, fragments, pages)
```

## Installation en local

Prérequis : Java 21 et MariaDB. Un compte Mailtrap gratuit permet de recevoir l'e-mail de réinitialisation du mot de passe.

1. Cloner le projet :
   ```
   git clone https://github.com/youssefbenkrid/reservations2026.git
   ```
2. Créer une base de données vide nommée `reservations`. Au premier démarrage, Flyway crée les tables et ajoute les données de départ.
   ```sql
   CREATE DATABASE reservations;
   ```
3. Définir les variables d'environnement listées dans `.env.example` : `DB_USERNAME`, `DB_PASSWORD`, `MAIL_USERNAME` et `MAIL_PASSWORD` (identifiants SMTP de la sandbox Mailtrap). Spring Boot ne lit pas le fichier `.env` tout seul : dans IntelliJ IDEA, renseignez-les dans *Run > Edit Configurations… > Environment variables*.
4. Lancer la classe `Reservations2026Application` depuis l'IDE, ou en ligne de commande une fois les variables définies :
   ```
   ./mvnw spring-boot:run
   ```
   Sous Windows : `mvnw.cmd spring-boot:run`
5. Ouvrir http://localhost:8080

**Comptes de démonstration** : `bob` (administrateur) et `anna` (membre), mot de passe `12345678`.

## Auteur

Youssef Ben Krid – [github.com/youssefbenkrid](https://github.com/youssefbenkrid)
