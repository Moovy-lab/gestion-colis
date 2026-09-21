package sahelys.gestioncolis.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sahelys.gestioncolis.back.dto.SuiviColisDto;
import sahelys.gestioncolis.back.enums.StatutColis;
import sahelys.gestioncolis.back.mapper.SuiviColisMapper;
import sahelys.gestioncolis.back.model.Colis;
import sahelys.gestioncolis.back.model.SuiviColis;
import sahelys.gestioncolis.back.repository.SuiviColisRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Cette classe implémente les opérations métier disponibles pour l'historique de suivi des colis.
 *
 * @author PC2
 */

@Service
@RequiredArgsConstructor
public class SuiviColisServiceImpl implements SuiviColisService {

    private final SuiviColisRepository suiviColisRepository;
    private final SuiviColisMapper suiviColisMapper;

    @Override
    public SuiviColis enregistrer(Colis colis, StatutColis statut, String commentaire) {
        SuiviColis suiviColis = new SuiviColis();
        suiviColis.setColis(colis);
        suiviColis.setStatut(statut);
        suiviColis.setDateStatut(LocalDateTime.now());
        suiviColis.setCommentaire(commentaire);
        return suiviColisRepository.save(suiviColis);
    }

    @Override
    public List<SuiviColisDto> recupererHistorique(Long idColis) {
        return suiviColisRepository.findByColis_IdColisOrderByDateStatutAsc(idColis).stream()
                .map(suiviColisMapper::toDto)
                .toList();
    }
}
