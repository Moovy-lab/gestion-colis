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
 * Cette classe permet d'avoir une traçabilité des colis envoyés.
 *
 * @author PC2
 */

@Entity
@Table(name = "suiviColis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuiviColis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSuivi")
    private Long idSuivi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutColis statut;

    @Column(nullable = false, length = 100)
    private LocalDateTime dateStatut;

    @Column(nullable = false, length = 255)
    private String commentaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idColis", nullable = false)
    private Colis colis;

    @Override
    public String toString() {
        return String.format(
                "┌─────────────────────────────────────────┐%n" +
                        "│ Statut du colis      : %-30s│%n" +
                        "│ Date de statut  : %-30s│%n" +
                        "│ Commentaire : %-30s│%n" +
                        "└─────────────────────────────────────────┘",
                statut, dateStatut, commentaire
        );
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idSuivi);
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
        final SuiviColis other = (SuiviColis) obj;
        return Objects.equals(this.idSuivi, other.idSuivi);
    }
}
