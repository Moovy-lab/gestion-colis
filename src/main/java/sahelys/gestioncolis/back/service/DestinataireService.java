package sahelys.gestioncolis.back.service;

import sahelys.gestioncolis.back.dto.DestinataireDto;
import sahelys.gestioncolis.back.model.Destinataire;

/**
 * Cette interface définit les opérations métier disponibles pour les destinataires.
 *
 * @author PC2
 */

public interface DestinataireService {
    Destinataire recupererOuCreer(DestinataireDto destinataireDto);
}
