package sahelys.gestioncolis.back.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe contient les données nécessaires à la création d'une expédition.
 *
 * @author PC2
 */

@Data
@NoArgsConstructor
public class ExpeditionCreationDto {

    @NotNull(message = "L'expéditeur est obligatoire")
    private Long idClientExpediteur;

    @NotNull(message = "Le destinataire est obligatoire")
    @Valid
    private DestinataireDto destinataire;

    @NotNull(message = "L'agence de départ est obligatoire")
    private Integer idAgenceDepart;

    @NotNull(message = "L'agence d'arrivée est obligatoire")
    private Integer idAgenceArrivee;

    @NotNull(message = "Le colis est obligatoire")
    @Valid
    private ColisCreationDto colis;
}
