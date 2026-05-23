package org.gateway.presentation;

import org.gateway.application.interfaces.ClientApi;
import org.gateway.config.BankApiConfig;
import org.gateway.infrastructure.DTO.GatewayAccountDTO;
import org.gateway.infrastructure.DTO.GatewayFriendsAccountsDTO;
import org.gateway.infrastructure.DTO.GatewayUserDTO;
import org.gateway.infrastructure.requestEntities.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ClientApiImpl implements ClientApi {

    private final RestTemplate restTemplate;
    private final BankApiConfig bankApiConfig;

    private HttpEntity<Void> buildEntityWithAuth(Authentication auth) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getCredentials().toString());
        return new HttpEntity<>(headers);
    }

    @Override
    public GatewayUserDTO getSelf(Authentication auth) {
        HttpEntity<Void> entity = buildEntityWithAuth(auth);
        String login = auth.getName();
        ResponseEntity<GatewayUserDTO> response = restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/users/{login}",
                HttpMethod.GET,
                entity,
                GatewayUserDTO.class,
                login
        );

        return response.getBody();
    }

    @Override
    public List<GatewayAccountDTO> getMyAccounts(Authentication auth) {
        HttpEntity<Void> entity = buildEntityWithAuth(auth);

        GatewayUserDTO user = getSelf(auth);
        Long userId = user.getId();

        ResponseEntity<GatewayAccountDTO[]> response = restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/users/{userId}/accounts",
                HttpMethod.GET,
                entity,
                GatewayAccountDTO[].class,
                userId
        );

        if (response.getBody() == null) {
            return List.of();
        }
        return List.of(response.getBody());
    }

    @Override
    public GatewayAccountDTO getAccountById(Long id, Authentication auth) {
        HttpEntity<Void> entity = buildEntityWithAuth(auth);
        GatewayUserDTO user = getSelf(auth);
        Long userId = user.getId();
        ResponseEntity<GatewayAccountDTO> response = restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/users/{userId}/accounts/{id}",
                HttpMethod.GET,
                entity,
                GatewayAccountDTO.class,
                userId,
                id
        );

        return response.getBody();
    }

    @Override
    public List<GatewayFriendsAccountsDTO> getFriendsAccounts(Authentication auth) {
        HttpEntity<Void> entity = buildEntityWithAuth(auth);
        GatewayUserDTO user = getSelf(auth);
        Long userId = user.getId();

        ResponseEntity<GatewayFriendsAccountsDTO[]> response = restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/users/{userId}/friends",
                HttpMethod.GET,
                entity,
                GatewayFriendsAccountsDTO[].class,
                userId
        );

        if (response.getBody() == null) {
            return List.of();
        }
        return List.of(response.getBody());
    }

    @Override
    public void transfer(TransferRequest transferRequest, Authentication auth) {
        HttpEntity<TransferRequest> entity = new HttpEntity<>(transferRequest, buildEntityWithAuth(auth).getHeaders());
        GatewayUserDTO user = getSelf(auth);
        Long userId = user.getId();

        restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/users/{userId}/transfer",
                HttpMethod.POST,
                entity,
                Void.class,
                userId
        );
    }

    @Override
    public void addFriend(Long friendId, Authentication auth) {
        HttpEntity<Void> entity = buildEntityWithAuth(auth);
        GatewayUserDTO user = getSelf(auth);
        Long userId = user.getId();

        restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/users/{userID}/add_friend/{friendId}",
                HttpMethod.POST,
                entity,
                Void.class,
                userId,
                friendId
        );
    }

    @Override
    public void removeFriend(Long friendId, Authentication auth) {
        HttpEntity<Void> entity = buildEntityWithAuth(auth);
        GatewayUserDTO user = getSelf(auth);
        Long userId = user.getId();

        restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/users/{userID}/delete_friend/{friendId}",
                HttpMethod.DELETE,
                entity,
                Void.class,
                userId,
                friendId
        );
    }

    @Override
    public void deposit(Long id, double amount, Authentication auth) {
        HttpEntity<Void> entity = buildEntityWithAuth(auth);
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        uriVariables.put("amount", amount);

        restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/accounts/{id}/deposit?amount={amount}",
                HttpMethod.POST,
                entity,
                Void.class,
                uriVariables
        );
    }

    @Override
    public void withdraw(Long id, double amount, Authentication auth) {
        HttpEntity<Void> entity = buildEntityWithAuth(auth);
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        uriVariables.put("amount", amount);

        restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/accounts/{id}/withdraw?amount={amount}",
                HttpMethod.POST,
                entity,
                Void.class,
                uriVariables
        );
    }
}
