package sahelys.gestioncolis.back.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Cette classe permet de connaître les différentes expéditions effectuées.
 *
 * @author PC2
 */
@Entity
@Table(name = "expedition")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expedition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExpedition")
    private Long idExpedition;

    @Column(nullable = false, length = 100)
    private LocalDateTime dateExpedition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient", nullable = false)
    private Client expediteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDestinataire", nullable = false)
    private Destinataire destinataire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAgenceDepart", nullable = false)
    private Agence agenceDepart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAgenceArrivee", nullable = false)
    private Agence agenceArrivee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idColis", nullable = false, unique = true)
    private Colis colis;

    @Override
    public String toString() {
        return String.format(
                "┌─────────────────────────────────────────┐%n" +
                        "│ Date de l'expédition   : %-30s│%n" +
                        "└─────────────────────────────────────────┘",
                dateExpedition
        );
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idExpedition);
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
        final Expedition other = (Expedition) obj;
        return Objects.equals(this.idExpedition, other.idExpedition);
    }
}
