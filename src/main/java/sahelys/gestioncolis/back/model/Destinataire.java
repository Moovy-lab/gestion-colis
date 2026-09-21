package sahelys.gestioncolis.back.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Cette classe contient les données du destinataire.
 * Nom, prenom, telephone sont obligatoires.
 * Deux destinataires ne peuvent pas avoir le même numéro de téléphone.
 *
 * @author PC2
 */

@Entity
@Table(name = "destinataire")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Destinataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDestinataire")
    private Long idDestinataire;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(nullable = false, length = 100, unique = true)
    private String telephone;

    @Override
    public String toString() {
        return String.format(
                "┌─────────────────────────────────────────┐%n" +
                        "│ Nom du client      : %-30s│%n" +
                        "│ Prenom du client   : %-30s│%n" +
                        "│ Numero de telephone du client : %-30s│%n" +
                        "└─────────────────────────────────────────┘",
                nom, prenom, telephone
        );
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idDestinataire);
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
        final Destinataire other = (Destinataire) obj;
        return Objects.equals(this.idDestinataire, other.idDestinataire);
    }
}
