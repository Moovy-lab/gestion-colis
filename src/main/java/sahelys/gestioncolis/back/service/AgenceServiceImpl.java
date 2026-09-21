package sahelys.gestioncolis.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sahelys.gestioncolis.back.dto.AgenceDto;
import sahelys.gestioncolis.back.exception.DataNotFoundException;
import sahelys.gestioncolis.back.mapper.AgenceMapper;
import sahelys.gestioncolis.back.model.Agence;
import sahelys.gestioncolis.back.repository.AgenceRepository;

import java.util.List;


/**
 * Cette classe implémente les opérations métier disponibles pour les agences.
 *
 * @author PC2
 */

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements AgenceService {

    private final AgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    @Override
    public AgenceDto ajouter(AgenceDto agenceDto) {
        Agence agenceSaved = agenceRepository.save(agenceMapper.toEntity(agenceDto));
        return agenceMapper.toDto(agenceSaved);
    }

    @Override
    public AgenceDto modifier(AgenceDto agenceDto) {
        Agence agenceSaved = agenceRepository.save(agenceMapper.toEntity(agenceDto));
        return agenceMapper.toDto(agenceSaved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgenceDto> recupererListeDesContacts() {
        return agenceRepository.findAll().stream().map(agence -> agenceMapper.toDto(agence)).toList();
    }

    @Override
    public AgenceDto findById(Integer idAgence) {
        return agenceRepository.findById(idAgence).map(agenceMapper::toDto).orElseThrow(()-> new DataNotFoundException("Auteur introuvable"));
    }
}
