package sahelys.gestioncolis.back.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Cette classe contient les données des clients.
 * Nom, prenom, telephone sont obligatoires.
 * Deux clients ne peuvent pas avoir le même numéro de téléphone.
 *
 * @author PC2
 */

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClient")
    private Long idClient;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 150)
    private String prenom;

    @Column(nullable = false, length = 30, unique = true)
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
        hash = 71 * hash + Objects.hashCode(this.idClient);
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
        final Client other = (Client) obj;
        return Objects.equals(this.idClient, other.idClient);
    }


}
