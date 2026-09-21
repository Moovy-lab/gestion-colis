package sahelys.gestioncolis.back.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import sahelys.gestioncolis.back.enums.StatutColis;

import java.time.LocalDateTime;

/**
 * Cette classe représente les données échangées pour un colis.
 *
 * @author PC2
 */

@Data
@NoArgsConstructor
public class ColisDto {

    private Long idColis;

    @NotBlank(message = "Le numero de suivi est obligatoire et unique")
    @Size(max = 30, message = "Le nombre de caractère du numero ne doit pas dépasser 30")
    private String numeroSuivi;

    @NotBlank(message = "La description est obligatoire")
    @Size(max = 100, message = "Le nombre de caractère de la description ne doit pas dépasser 100")
    private String description;

    @Positive(message = "Le poids doit être supérieur à 0")
    private float poids;

    @Positive(message = "La longueur doit être supérieure à 0")
    private float longueur;

    @Positive(message = "La largeur doit être supérieure à 0")
    private float largeur;

    @Positive(message = "La hauteur doit être supérieure à 0")
    private float hauteur;

    @NotNull(message = "La valeur déclarée est obligatoire")
    @PositiveOrZero(message = "La valeur déclarée ne peut pas être négative")
    private Long valeurDeclare;

    @NotNull(message = "Le statut est obligatoire")
    private StatutColis statut;

    @NotNull(message = "La date de création est obligatoire")
    private LocalDateTime dateCreation;

    @NotBlank(message = "Le code de retrait est obligatoire")
    @Size(min = 6, max = 6, message = "Le code de retrait doit contenir exactement 6 caractères")
    private String codeRetrait;
}
