package sahelys.gestioncolis.back.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Cette classe représente les données échangées pour une agence.
 *
 * @author PC2
 */

@Data
@NoArgsConstructor
public class AgenceDto {

    private Integer idAgence;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 50, message = "Le nombre de caractère du nom ne doit pas dépasser 50")
    private String nom;

    @NotBlank(message = "La ville est obligatoire")
    @Size(max = 50, message = "Le nombre de caractère de la ville ne doit pas dépasser 100")
    private String ville;

    @NotBlank(message = "Le téléphone est obligatoire")
    @Size(max = 50, message = "Le nombre de chiffre du téléphone ne doit pas dépasser 50")
    private String telephone;

}
