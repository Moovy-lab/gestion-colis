package sahelys.gestioncolis.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sahelys.gestioncolis.back.dto.ChangerStatutDto;
import sahelys.gestioncolis.back.dto.ColisCreationDto;
import sahelys.gestioncolis.back.dto.ColisDto;
import sahelys.gestioncolis.back.dto.ReceptionDto;
import sahelys.gestioncolis.back.enums.StatutColis;
import sahelys.gestioncolis.back.exception.ChampNotValidateException;
import sahelys.gestioncolis.back.exception.DataNotFoundException;
import sahelys.gestioncolis.back.mapper.ColisMapper;
import sahelys.gestioncolis.back.model.Colis;
import sahelys.gestioncolis.back.repository.ColisRepository;
import sahelys.gestioncolis.back.repository.ExpeditionRepository;
import sahelys.gestioncolis.back.repository.SuiviColisRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Cette classe implémente les opérations métier disponibles pour les colis,
 * notamment les règles de transition de statut et de réception.
 *
 * @author PC2
 */

@Service
@RequiredArgsConstructor
public class ColisServiceImpl implements ColisService {

    private final ColisRepository colisRepository;
    private final ColisMapper colisMapper;
    private final ColisCodeGenerator colisCodeGenerator;
    private final SuiviColisService suiviColisService;
    private final SuiviColisRepository suiviColisRepository;
    private final ExpeditionRepository expeditionRepository;

    @Override
    public ColisDto findById(Long idColis) {
        return colisRepository.findById(idColis)
                .map(colisMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("Colis introuvable"));
    }

    @Override
    public List<ColisDto> recupererListeDesColis() {
        return colisRepository.findAll().stream().map(colisMapper::toDto).toList();
    }

    @Override
    public ColisDto rechercherParNumeroSuivi(String numeroSuivi) {
        return colisRepository.findByNumeroSuivi(numeroSuivi)
                .map(colisMapper::toDto)
                .orElseThrow(() -> new DataNotFoundException("Colis introuvable pour ce numéro de suivi"));
    }

    @Override
    public StatutColis recupererStatutActuel(Long idColis) {
        return colisRepository.findById(idColis)
                .map(Colis::getStatut)
                .orElseThrow(() -> new DataNotFoundException("Colis introuvable"));
    }

    @Override
    @Transactional
    public ColisDto faireEvoluerStatut(Long idColis, ChangerStatutDto dto) {
        Colis colis = colisRepository.findById(idColis)
                .orElseThrow(() -> new DataNotFoundException("Colis introuvable"));

        if (dto.getStatut() == StatutColis.LIVRE) {
            throw new ChampNotValidateException("Le statut LIVRE doit être défini via le processus de réception");
        }
        if (!colis.getStatut().peutTransitionnerVers(dto.getStatut())) {
            throw new ChampNotValidateException(
                    "Transition de statut invalide : " + colis.getStatut() + " -> " + dto.getStatut());
        }

        colis.setStatut(dto.getStatut());
        Colis colisSaved = colisRepository.save(colis);
        suiviColisService.enregistrer(colisSaved, dto.getStatut(), dto.getCommentaire());

        return colisMapper.toDto(colisSaved);
    }

    @Override
    @Transactional
    public ColisDto reception(ReceptionDto dto) {
        Colis colis = colisRepository.findByNumeroSuivi(dto.getNumeroSuivi())
                .orElseThrow(() -> new DataNotFoundException("Colis introuvable pour ce numéro de suivi"));

        if (colis.getStatut() != StatutColis.ARRIVE_AGENCE) {
            throw new ChampNotValidateException("Le colis doit être au statut ARRIVE_AGENCE pour être réceptionné");
        }
        if (!dto.getCodeRetrait().equals(colis.getCodeRetrait())) {
            throw new ChampNotValidateException("Code de retrait invalide");
        }

        colis.setStatut(StatutColis.LIVRE);
        Colis colisSaved = colisRepository.save(colis);
        suiviColisService.enregistrer(colisSaved, StatutColis.LIVRE, "Colis réceptionné");

        return colisMapper.toDto(colisSaved);
    }

    @Override
    @Transactional
    public Colis creerColisInitial(ColisCreationDto colisCreationDto) {
        Colis colis = new Colis();
        colis.setNumeroSuivi(colisCodeGenerator.genererNumeroSuivi());
        colis.setCodeRetrait(colisCodeGenerator.genererCodeRetrait());
        colis.setDescription(colisCreationDto.getDescription());
        colis.setPoids(colisCreationDto.getPoids());
        colis.setLongueur(colisCreationDto.getLongueur());
        colis.setLargeur(colisCreationDto.getLargeur());
        colis.setHauteur(colisCreationDto.getHauteur());
        colis.setValeurDeclare(colisCreationDto.getValeurDeclare());
        colis.setStatut(StatutColis.ENREGISTRE);
        colis.setDateCreation(LocalDateTime.now());

        Colis colisSaved = colisRepository.save(colis);
        suiviColisService.enregistrer(colisSaved, StatutColis.ENREGISTRE, "Colis enregistré");

        return colisSaved;
    }

    @Override
    @Transactional
    public void supprimer(Long idColis) {
        Colis colis = colisRepository.findById(idColis)
                .orElseThrow(() -> new DataNotFoundException("Colis introuvable"));

        if (colis.getStatut() != StatutColis.ENREGISTRE) {
            throw new ChampNotValidateException("Un colis ne peut être supprimé après son expédition");
        }

        suiviColisRepository.deleteByColis_IdColis(idColis);
        expeditionRepository.findByColis_IdColis(idColis).ifPresent(expeditionRepository::delete);
        colisRepository.delete(colis);
    }
}
