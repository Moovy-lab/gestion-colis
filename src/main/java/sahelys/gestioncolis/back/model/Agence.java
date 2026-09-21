package sahelys.gestioncolis.back.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Cette classe contient les données des agences.
 * Nom, ville, telephone sont obligatoires.
 * Deux agences ne peuvent pas avoir le même numéro de téléphone.
 *
 * @author PC2
 */

@Entity
@Table(name = "agence")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAgence")
    private Integer idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 150)
    private String ville;

    @Column(nullable = false, length = 30, unique = true)
    private String telephone;

    @Override
    public String toString() {
        return String.format(
                "┌─────────────────────────────────────────┐%n" +
                        "│ Nom de l'agence      : %-30s│%n" +
                        "│ Ville de l'agence   : %-30s│%n" +
                        "│ Telephone de l'agence : %-30s│%n" +
                        "└─────────────────────────────────────────┘",
                nom, ville, telephone
        );
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idAgence);
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
        final Agence other = (Agence) obj;
        return Objects.equals(this.idAgence, other.idAgence);
    }
}
