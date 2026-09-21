package sahelys.gestioncolis.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sahelys.gestioncolis.back.dto.ClientDto;
import sahelys.gestioncolis.back.exception.DataNotFoundException;
import sahelys.gestioncolis.back.mapper.ClientMapper;
import sahelys.gestioncolis.back.model.Client;
import sahelys.gestioncolis.back.repository.ClientRepository;

import java.util.List;

/**
 * Cette classe implémente les opérations métier disponibles pour les clients.
 *
 * @author PC2
 */

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto ajouter(ClientDto clientDto) {
        Client clientSaved = clientRepository.save(clientMapper.toEntity(clientDto));
        return clientMapper.toDto(clientSaved);
    }

    @Override
    public ClientDto modifier(ClientDto clientDto) {
        Client clientSaved = clientRepository.save(clientMapper.toEntity(clientDto));
        return clientMapper.toDto(clientSaved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDto> recupererListeDesClients() {
        return clientRepository.findAll().stream().map(client -> clientMapper.toDto(client)).toList();
    }

    @Override
    public ClientDto findById(Long idClient) {
        return clientRepository.findById(idClient).map(clientMapper::toDto).orElseThrow(()-> new DataNotFoundException("Client introuvable"));
    }
}
