package sahelys.gestioncolis.back.service;

import sahelys.gestioncolis.back.dto.ChangerStatutDto;
import sahelys.gestioncolis.back.dto.ColisCreationDto;
import sahelys.gestioncolis.back.dto.ColisDto;
import sahelys.gestioncolis.back.dto.ReceptionDto;
import sahelys.gestioncolis.back.enums.StatutColis;
import sahelys.gestioncolis.back.model.Colis;

import java.util.List;

/**
 * Cette interface définit les opérations métier disponibles pour les colis,
 * notamment le suivi et la réception.
 *
 * @author PC2
 */

public interface ColisService {
    ColisDto findById(Long idColis);

    List<ColisDto> recupererListeDesColis();

    ColisDto rechercherParNumeroSuivi(String numeroSuivi);

    StatutColis recupererStatutActuel(Long idColis);

    ColisDto faireEvoluerStatut(Long idColis, ChangerStatutDto dto);

    ColisDto reception(ReceptionDto dto);

    Colis creerColisInitial(ColisCreationDto colisCreationDto);

    void supprimer(Long idColis);
}
