# Gestion de Colis — API

API REST Spring Boot pour la gestion d'une société de messagerie : agences, clients,
expéditions, colis, suivi et réception.

## Stack technique

- Java 21
- Spring Boot 4.1.1 (Web, Data JPA, Validation)
- PostgreSQL
- MapStruct (mapping entité ↔ DTO)
- Lombok

## Prérequis

- JDK 21
- PostgreSQL en écoute sur `localhost:5433`, avec une base `colis`

## Installation

1. Créer la base et le schéma :

   ```bash
   createdb -p 5433 colis
   psql -p 5433 -d colis -f database/schema.sql
   ```

   (Alternative : laisser `spring.jpa.hibernate.ddl-auto: update`, déjà configuré dans
   `application.yaml`, créer le schéma automatiquement au premier démarrage.)

2. Adapter si besoin les identifiants de connexion dans
   `src/main/resources/application.yaml` :

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5433/colis
       username: postgres
       password: ghost
   ```

3. Lancer l'application :

   ```bash
   ./mvnw spring-boot:run
   ```

   L'API démarre sur `http://localhost:8080`.

## Documentation du modèle de données

- [database/dictionnaire-donnees.md](database/dictionnaire-donnees.md) — dictionnaire de données (tables, colonnes, contraintes)
- [database/schema.sql](database/schema.sql) — script SQL de création du schéma
- [docs/diagramme-classes.md](docs/diagramme-classes.md) — diagramme de classes du modèle de domaine

## Règles métier

- Le poids d'un colis doit être strictement supérieur à 0.
- Le numéro de suivi d'un colis est unique, généré automatiquement à la création
  (format `COL-yyyyMMdd-XXXXXX`).
- L'agence de départ et l'agence d'arrivée d'une expédition doivent être différentes.
- Le statut d'un colis ne peut progresser que dans l'ordre :
  `ENREGISTRE → PRISE_EN_CHARGE → EN_TRANSIT → ARRIVE_AGENCE`. Aucun saut d'étape n'est
  autorisé.
- Le passage au statut `LIVRE` ne peut se faire que via le processus de réception
  (vérification du code de retrait), jamais via l'endpoint générique de changement de
  statut.
- Chaque changement de statut est historisé (table `suivi_colis`), y compris
  l'enregistrement initial.
- Un colis ne peut être supprimé que tant qu'il est au statut `ENREGISTRE` (avant sa
  prise en charge) ; au-delà, la suppression est refusée.

## Points d'entrée de l'API

### Agences — `/agences`

| Méthode | Chemin | Description |
|---|---|---|
| POST | `/agences` | Créer une agence |
| GET | `/agences` | Lister les agences |
| GET | `/agences/{id}` | Récupérer une agence |
| PUT | `/agences/{id}` | Modifier une agence |

### Clients — `/clients`

| Méthode | Chemin | Description |
|---|---|---|
| POST | `/clients` | Créer un client (expéditeur) |
| GET | `/clients` | Lister les clients |
| GET | `/clients/{id}` | Récupérer un client |
| PUT | `/clients/{id}` | Modifier un client |

### Expéditions — `/expeditions`

| Méthode | Chemin | Description |
|---|---|---|
| POST | `/expeditions` | Créer une expédition (crée le colis associé, génère numéro de suivi et code de retrait) |
| GET | `/expeditions` | Lister les expéditions |
| GET | `/expeditions/{id}` | Consulter une expédition (avec colis, expéditeur, destinataire, agences) |

### Colis / Suivi / Réception — `/colis`

| Méthode | Chemin | Description |
|---|---|---|
| GET | `/colis` | Lister les colis |
| GET | `/colis/{id}` | Récupérer un colis |
| GET | `/colis/recherche?numeroSuivi=...` | Rechercher un colis par numéro de suivi |
| GET | `/colis/{id}/statut` | Consulter le statut actuel |
| GET | `/colis/{id}/historique` | Consulter l'historique des statuts |
| PATCH | `/colis/{id}/statut` | Faire évoluer le statut (sauf vers `LIVRE`) |
| POST | `/colis/reception` | Réceptionner un colis (numéro de suivi + code de retrait) |
| DELETE | `/colis/{id}` | Supprimer un colis (uniquement si `ENREGISTRE`) |

## Gestion des erreurs

Toute erreur renvoie un corps JSON uniforme :

```json
{
  "errorDate": "2026-09-21T15:34:50.443",
  "status": "404 NOT_FOUND",
  "message": "Colis introuvable",
  "debugMessage": "..."
}
```

| Code | Cas |
|---|---|
| 400 | Validation de champ invalide, règle métier violée (transition de statut, agences identiques, code de retrait incorrect...) |
| 404 | Ressource introuvable |
| 409 | Contrainte d'unicité violée (numéro de téléphone déjà utilisé...) |

## Scénario type

Un client dépose un colis à Ouagadougou à destination de Bobo-Dioulasso :

1. `POST /expeditions` — crée l'expédition, génère le numéro de suivi et le code de retrait.
2. `PATCH /colis/{id}/statut` (`PRISE_EN_CHARGE`, `EN_TRANSIT`, `ARRIVE_AGENCE`) — fait progresser le colis étape par étape.
3. `POST /colis/reception` — remet le colis au destinataire après vérification du code de retrait ; passage au statut `LIVRE`.
4. `GET /colis/{id}/historique` — trace complète de toutes les étapes, horodatées.
