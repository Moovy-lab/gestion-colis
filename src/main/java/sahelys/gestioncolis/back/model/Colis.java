package sahelys.gestioncolis.back.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sahelys.gestioncolis.back.enums.StatutColis;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Cette classe contient les données des colis.
 * Deux colis ne peuvent pas avoir le même numéro de suivi.
 *
 * @author PC2
 */

@Entity
@Table(name = "colis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Colis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idColis")
    private Long idColis;

    @Column(nullable = false, length = 30, unique = true)
    private String numeroSuivi;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, length = 50)
    private float poids;

    @Column(nullable = false, length = 10)
    private float longueur;

    @Column(nullable = false, length = 10)
    private float largeur;

    @Column(nullable = false, length = 10)
    private float hauteur;

    @Column(nullable = false, length = 10)
    private Long valeurDeclare;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private StatutColis statut;

    @Column(nullable = false, length = 100)
    private LocalDateTime dateCreation;

    @Column(nullable = false, length = 6)
    private String codeRetrait;

    public String toString() {
        return String.format(
                "┌─────────────────────────────────────────┐%n" +
                        "│ Numero de suivi       : %-30s│%n" +
                        "│ Description du colis    : %-30s│%n" +
                        "│ Poids du colis : %-30s│%n" +
                        "| Longueur du colis : %-30s│%n" +
                        "| Largeur du colis : %-30s│%n" +
                        "| Hauteur du colis : %-30s│%n" +
                        "| Valeur déclaré du colis : %-30s│%n" +
                        "| Statut du colis : %-30s│%n" +
                        "| Date de création : %-30s│%n" +
                        "└─────────────────────────────────────────┘",
                numeroSuivi, description, poids, longueur, largeur, hauteur, valeurDeclare, statut, dateCreation
        );
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idColis);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Colis other = (Colis) obj;
        return Objects.equals(this.idColis, other.idColis);
    }

}
