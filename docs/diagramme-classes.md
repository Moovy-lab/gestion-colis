# Diagramme de classes

Diagramme du modèle de domaine (couche `model`), correspondant aux entités JPA et à
leurs relations.

```mermaid
classDiagram
    class Agence {
        -Integer idAgence
        -String nom
        -String ville
        -String telephone
    }

    class Client {
        -Long idClient
        -String nom
        -String prenom
        -String telephone
    }

    class Destinataire {
        -Long idDestinataire
        -String nom
        -String prenom
        -String telephone
    }

    class Colis {
        -Long idColis
        -String numeroSuivi
        -String codeRetrait
        -String description
        -float poids
        -float longueur
        -float largeur
        -float hauteur
        -Long valeurDeclare
        -StatutColis statut
        -LocalDateTime dateCreation
    }

    class Expedition {
        -Long idExpedition
        -LocalDateTime dateExpedition
    }

    class SuiviColis {
        -Long idSuivi
        -StatutColis statut
        -LocalDateTime dateStatut
        -String commentaire
    }

    class StatutColis {
        <<enumeration>>
        ENREGISTRE
        PRISE_EN_CHARGE
        EN_TRANSIT
        ARRIVE_AGENCE
        LIVRE
    }

    Expedition "1" --> "1" Client : expediteur
    Expedition "1" --> "1" Destinataire : destinataire
    Expedition "1" --> "1" Agence : agenceDepart
    Expedition "1" --> "1" Agence : agenceArrivee
    Expedition "1" --> "1" Colis : colis
    SuiviColis "*" --> "1" Colis : colis
    Colis ..> StatutColis
    SuiviColis ..> StatutColis
```

## Notes de lecture

- **Expedition** est le pivot du modèle : elle relie en une seule création un
  **Client** (expéditeur), un **Destinataire**, deux **Agence** distinctes (départ et
  arrivée) et exactement un **Colis** (relation 1-1, portée par `Expedition`, qui détient
  la clé étrangère `id_colis`).
- **SuiviColis** journalise (1 à N) chaque changement de statut d'un **Colis** ; une ligne
  y est ajoutée automatiquement à chaque étape (y compris l'enregistrement initial).
- `Agence`, `Client` et `Destinataire` n'ont pas de référence retour vers `Expedition` —
  les relations sont unidirectionnelles, portées côté `Expedition`/`SuiviColis`.
- Le statut d'un **Colis** ne peut progresser que dans l'ordre de l'énumération
  `StatutColis` (`ENREGISTRE → PRISE_EN_CHARGE → EN_TRANSIT → ARRIVE_AGENCE`), la
  transition vers `LIVRE` n'étant possible que via le processus de réception.
