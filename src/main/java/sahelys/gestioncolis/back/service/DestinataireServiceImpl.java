package sahelys.gestioncolis.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sahelys.gestioncolis.back.dto.DestinataireDto;
import sahelys.gestioncolis.back.mapper.DestinataireMapper;
import sahelys.gestioncolis.back.model.Destinataire;
import sahelys.gestioncolis.back.repository.DestinataireRepository;

/**
 * Cette classe implémente les opérations métier disponibles pour les destinataires.
 *
 * @author PC2
 */

@Service
@RequiredArgsConstructor
public class DestinataireServiceImpl implements DestinataireService {

    private final DestinataireRepository destinataireRepository;
    private final DestinataireMapper destinataireMapper;

    @Override
    public Destinataire recupererOuCreer(DestinataireDto destinataireDto) {
        return destinataireRepository.findByTelephone(destinataireDto.getTelephone())
                .orElseGet(() -> destinataireRepository.save(destinataireMapper.toEntity(destinataireDto)));
    }
}
