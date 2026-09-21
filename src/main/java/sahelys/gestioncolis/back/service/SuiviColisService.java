package sahelys.gestioncolis.back.service;

import sahelys.gestioncolis.back.dto.SuiviColisDto;
import sahelys.gestioncolis.back.enums.StatutColis;
import sahelys.gestioncolis.back.model.Colis;
import sahelys.gestioncolis.back.model.SuiviColis;

import java.util.List;

/**
 * Cette interface définit les opérations métier disponibles pour l'historique de suivi des colis.
 *
 * @author PC2
 */

public interface SuiviColisService {
    SuiviColis enregistrer(Colis colis, StatutColis statut, String commentaire);

    List<SuiviColisDto> recupererHistorique(Long idColis);
}
