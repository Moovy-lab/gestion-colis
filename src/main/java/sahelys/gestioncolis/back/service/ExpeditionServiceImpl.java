package sahelys.gestioncolis.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sahelys.gestioncolis.back.dto.ExpeditionCreationDto;
import sahelys.gestioncolis.back.dto.ExpeditionDto;
import sahelys.gestioncolis.back.exception.ChampNotValidateException;
import sahelys.gestioncolis.back.exception.DataNotFoundException;
import sahelys.gestioncolis.back.mapper.ExpeditionMapper;
import sahelys.gestioncolis.back.model.Agence;
import sahelys.gestioncolis.back.model.Client;
import sahelys.gestioncolis.back.model.Colis;
import sahelys.gestioncolis.back.model.Destinataire;
import sahelys.gestioncolis.back.model.Expedition;
import sahelys.gestioncolis.back.repository.AgenceRepository;
import sahelys.gestioncolis.back.repository.ClientRepository;
import sahelys.gestioncolis.back.repository.ExpeditionRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Cette classe implémente les opérations métier disponibles pour les expéditions.
 *
 * @author PC2
 */

@Service
@RequiredArgsConstructor
public class ExpeditionServiceImpl implements ExpeditionService {

    private final ExpeditionRepository expeditionRepository;
    private final ExpeditionMapper expeditionMapper;
    private final ClientRepository clientRepository;
    private final AgenceRepository agenceRepository;
    private final DestinataireService destinataireService;
    private final ColisService colisService;

    @Override
    @Transactional
    public ExpeditionDto creerExpedition(ExpeditionCreationDto dto) {
        Client expediteur = clientRepository.findById(dto.getIdClientExpediteur())
                .orElseThrow(() -> new DataNotFoundException("Client expéditeur introuvable"));

        Agence agenceDepart = agenceRepository.findById(dto.getIdAgenceDepart())
                .orElseThrow(() -> new DataNotFoundException("Agence de départ introuvable"));

        Agence agenceArrivee = agenceRepository.findById(dto.getIdAgenceArrivee())
                .orElseThrow(() -> new DataNotFoundException("Agence d'arrivée introuvable"));

        if (agenceDepart.getIdAgence().equals(agenceArrivee.getIdAgence())) {
            throw new ChampNotValidateException("L'agence de départ et l'agence d'arrivée doivent être différentes");
        }

        Destinataire destinataire = destinataireService.recupererOuCreer(dto.getDestinataire());
        Colis colis = colisService.creerColisInitial(dto.getColis());

        Expedition expedition = new Expedition();
        expedition.setDateExpedition(LocalDateTime.now());
        expedition.setExpediteur(expediteur);
        expedition.setDestinataire(destinataire);
        expedition.setAgenceDepart(agenceDepart);
        expedition.setAgenceArrivee(agenceArrivee);
        expedition.setColis(colis);

        Expedition expeditionSaved = expeditionRepository.save(expedition);
        return expeditionMapper.toDto(expeditionSaved);
    }

    @Override
    public ExpeditionDto findById(Long idExpedition) {
        return expeditionRepository.findById(idExpedition)
                .map(expeditionMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("Expédition introuvable"));
    }

    @Override
    public List<ExpeditionDto> recupererListeDesExpeditions() {
        return expeditionRepository.findAll().stream().map(expeditionMapper::toDto).toList();
    }
}
