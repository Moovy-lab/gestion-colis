package sahelys.gestioncolis.back.service;

import sahelys.gestioncolis.back.dto.ExpeditionCreationDto;
import sahelys.gestioncolis.back.dto.ExpeditionDto;

import java.util.List;

/**
 * Cette interface définit les opérations métier disponibles pour les expéditions.
 *
 * @author PC2
 */

public interface ExpeditionService {
    ExpeditionDto creerExpedition(ExpeditionCreationDto dto);

    ExpeditionDto findById(Long idExpedition);

    List<ExpeditionDto> recupererListeDesExpeditions();
}
