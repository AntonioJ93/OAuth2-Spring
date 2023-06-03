package com.tutorial.autorizationserver.service;


import com.tutorial.autorizationserver.dto.CreateClientDto;
import com.tutorial.autorizationserver.dto.MessageDto;
import com.tutorial.autorizationserver.entity.Client;
import com.tutorial.autorizationserver.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;

    private Client clientFromDto(CreateClientDto createClientDto){
        Client client= Client.builder()
                .clientId(createClientDto.getClientId())
                .clientSecret(passwordEncoder.encode(createClientDto.getClientSecret()))
                .authenticationMethods(createClientDto.getAuthenticationMethods())
                .authorizationGrantTypes(createClientDto.getAuthorizationGrantTypes())
                .redirectUris(createClientDto.getRedirectUris())
                .scopes(createClientDto.getScopes())
                .requireProofKey(createClientDto.isRequireProofKey())
                .build();
        return client;
    }

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        Client client=clientRepository.findByClientId(id).orElseThrow(() -> new RuntimeException("Client no encontrado"));
        return Client.toRegisterClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client=clientRepository.findByClientId(clientId).orElseThrow(() -> new RuntimeException("Client no encontrado"));
        return Client.toRegisterClient(client);
    }

    public MessageDto create(CreateClientDto dto){
        Client client =clientFromDto(dto);
        Client guardado=clientRepository.save(client);
        return new MessageDto("clientId "+guardado.getClientId()+" guardado");
    }
}
