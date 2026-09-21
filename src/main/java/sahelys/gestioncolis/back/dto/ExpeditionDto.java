package sahelys.gestioncolis.back.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Cette classe représente les données détaillées d'une expédition,
 * avec le colis, l'expéditeur, le destinataire et les agences liés.
 *
 * @author PC2
 */

@Data
@NoArgsConstructor
public class ExpeditionDto {

    private Long idExpedition;

    private LocalDateTime dateExpedition;

    private ClientDto expediteur;

    private DestinataireDto destinataire;

    private AgenceDto agenceDepart;

    private AgenceDto agenceArrivee;

    private ColisDto colis;
}
