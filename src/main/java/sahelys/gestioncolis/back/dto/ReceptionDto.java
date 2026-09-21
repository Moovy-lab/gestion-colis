package sahelys.gestioncolis.back.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe contient les données nécessaires à la réception d'un colis.
 *
 * @author PC2
 */

@Data
@NoArgsConstructor
public class ReceptionDto {

    @NotBlank(message = "Le numéro de suivi est obligatoire")
    private String numeroSuivi;

    @NotBlank(message = "Le code de retrait est obligatoire")
    @Size(min = 6, max = 6, message = "Le code de retrait doit contenir exactement 6 caractères")
    private String codeRetrait;
}
