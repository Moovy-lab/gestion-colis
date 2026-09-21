package sahelys.gestioncolis.back.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe contient les données nécessaires à la création d'un colis.
 *
 * @author PC2
 */

@Data
@NoArgsConstructor
public class ColisCreationDto {

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
}
