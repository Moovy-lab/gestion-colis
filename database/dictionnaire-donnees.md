# Dictionnaire de données

Base PostgreSQL `colis`. Schéma défini dans [schema.sql](schema.sql).

## Table `agence`

| Colonne | Type | Contraintes | Description |
|---|---|---|---|
| id_agence | INTEGER | PK, auto-incrémenté | Identifiant unique de l'agence |
| nom | VARCHAR(100) | NOT NULL | Nom de l'agence |
| ville | VARCHAR(150) | NOT NULL | Ville où se situe l'agence |
| telephone | VARCHAR(30) | NOT NULL, UNIQUE | Numéro de téléphone de l'agence |

## Table `client`

Représente l'expéditeur d'une expédition.

| Colonne | Type | Contraintes | Description |
|---|---|---|---|
| id_client | BIGINT | PK, auto-incrémenté | Identifiant unique du client |
| nom | VARCHAR(100) | NOT NULL | Nom du client |
| prenom | VARCHAR(150) | NOT NULL | Prénom du client |
| telephone | VARCHAR(30) | NOT NULL, UNIQUE | Numéro de téléphone du client |

## Table `destinataire`

| Colonne | Type | Contraintes | Description |
|---|---|---|---|
| id_destinataire | BIGINT | PK, auto-incrémenté | Identifiant unique du destinataire |
| nom | VARCHAR(100) | NOT NULL | Nom du destinataire |
| prenom | VARCHAR(100) | NOT NULL | Prénom du destinataire |
| telephone | VARCHAR(100) | NOT NULL, UNIQUE | Numéro de téléphone du destinataire (sert de clé de recherche lors de la création d'une expédition, pour éviter les doublons) |

## Table `colis`

| Colonne | Type | Contraintes | Description |
|---|---|---|---|
| id_colis | BIGINT | PK, auto-incrémenté | Identifiant unique du colis |
| numero_suivi | VARCHAR(30) | NOT NULL, UNIQUE | Numéro de suivi généré automatiquement à la création (format `COL-yyyyMMdd-XXXXXX`) |
| code_retrait | VARCHAR(6) | NOT NULL | Code à 6 chiffres généré automatiquement, communiqué au destinataire, vérifié lors de la réception |
| description | VARCHAR(100) | NOT NULL | Description du contenu du colis |
| poids | REAL | NOT NULL, > 0 | Poids du colis (kg) |
| longueur | REAL | NOT NULL, > 0 | Longueur du colis (cm) |
| largeur | REAL | NOT NULL, > 0 | Largeur du colis (cm) |
| hauteur | REAL | NOT NULL, > 0 | Hauteur du colis (cm) |
| valeur_declare | BIGINT | NOT NULL, >= 0 | Valeur déclarée du colis |
| statut | VARCHAR(100) | NOT NULL, CHECK IN (ENREGISTRE, PRISE_EN_CHARGE, EN_TRANSIT, ARRIVE_AGENCE, LIVRE) | Statut courant du colis |
| date_creation | TIMESTAMP | NOT NULL | Date et heure de création du colis (dépôt) |

## Table `expedition`

Relie un colis (relation 1-1) à son expéditeur, son destinataire et ses deux agences.

| Colonne | Type | Contraintes | Description |
|---|---|---|---|
| id_expedition | BIGINT | PK, auto-incrémenté | Identifiant unique de l'expédition |
| date_expedition | TIMESTAMP | NOT NULL | Date et heure de création de l'expédition |
| id_client | BIGINT | NOT NULL, FK → client(id_client) | Expéditeur |
| id_destinataire | BIGINT | NOT NULL, FK → destinataire(id_destinataire) | Destinataire |
| id_agence_depart | INTEGER | NOT NULL, FK → agence(id_agence) | Agence de départ |
| id_agence_arrivee | INTEGER | NOT NULL, FK → agence(id_agence) | Agence d'arrivée (doit être différente de l'agence de départ) |
| id_colis | BIGINT | NOT NULL, UNIQUE, FK → colis(id_colis) | Colis transporté par cette expédition (relation 1-1) |

## Table `suivi_colis`

Historique append-only des changements de statut d'un colis (une ligne par transition, y compris l'enregistrement initial).

| Colonne | Type | Contraintes | Description |
|---|---|---|---|
| id_suivi | BIGINT | PK, auto-incrémenté | Identifiant unique de l'entrée d'historique |
| id_colis | BIGINT | NOT NULL, FK → colis(id_colis) | Colis concerné |
| statut | VARCHAR(255) | NOT NULL, CHECK IN (ENREGISTRE, PRISE_EN_CHARGE, EN_TRANSIT, ARRIVE_AGENCE, LIVRE) | Statut atteint à cette étape |
| date_statut | TIMESTAMP | NOT NULL | Date et heure du changement de statut |
| commentaire | VARCHAR(255) | NOT NULL | Commentaire libre associé au changement de statut |

## Modèle relationnel (résumé)

```
agence (1) ──< expedition >── (1) client
                  │
                  ├──── (1) destinataire
                  │
                  └──── (1:1) colis ──< (1:N) suivi_colis
```

- Une **expedition** référence exactement un **client** (expéditeur), un **destinataire**,
  deux **agence** (départ et arrivée, distinctes) et un seul **colis** (relation 1-1).
- Un **colis** possède un historique de **suivi_colis** (1 à N), une ligne étant ajoutée à
  chaque changement de statut, y compris l'enregistrement initial.
- `agence`, `client` et `destinataire` n'ont pas de référence retour vers les tables qui
  les utilisent (relations unidirectionnelles portées par `expedition`).
