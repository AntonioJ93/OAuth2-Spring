package com.tutorial.autorizationserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String clientId;

    private String clientSecret;
    //TODO: CAmbiar a OneToMany
    @ElementCollection(fetch = FetchType.EAGER)  //cambiar a OneToMany
    private Set<ClientAuthenticationMethod> authenticationMethods;

    @ElementCollection(fetch = FetchType.EAGER) //cambiar a OneToMany
    private Set<AuthorizationGrantType> authorizationGrantTypes;

    @ElementCollection(fetch = FetchType.EAGER) //cambiar a OneToMany
    private Set<String> redirectUris;

    @ElementCollection(fetch = FetchType.EAGER) //cambiar a OneToMany
    private Set<String> scopes;

    private boolean requireProofKey;

    public static RegisteredClient toRegisterClient(Client client){
        RegisteredClient.Builder builder= RegisteredClient.withId(client.getClientId());
        builder.clientId(client.getClientId())
                .clientSecret(client.getClientSecret()) //cifrar en el serivce
                .clientIdIssuedAt(new Date().toInstant())
                .clientAuthenticationMethods(am -> am.addAll(client.getAuthenticationMethods()))
                .authorizationGrantTypes(am -> am.addAll(client.getAuthorizationGrantTypes()))
                .redirectUris(am -> am.addAll(client.getRedirectUris()))
                .scopes(am -> am.addAll(client.getScopes()))
                .clientSettings(ClientSettings.builder()
                        .requireProofKey(client.isRequireProofKey())
                        .build());
        return builder.build();

    }

}
