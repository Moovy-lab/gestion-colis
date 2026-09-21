package sahelys.gestioncolis.back.service;

import jakarta.validation.Valid;
import sahelys.gestioncolis.back.dto.AgenceDto;

import java.util.List;

/**
 * Cette interface définit les opérations métier disponibles pour les agences.
 *
 * @author PC2
 */

public interface AgenceService {
    AgenceDto ajouter(@Valid AgenceDto agenceDto);

    AgenceDto modifier(AgenceDto agenceDto);

    List<AgenceDto> recupererListeDesContacts();

    AgenceDto findById(Integer idAgence);
}
