package org.gateway.presentation;

import org.gateway.application.interfaces.AdminApi;
import org.gateway.config.BankApiConfig;
import org.gateway.infrastructure.DTO.GatewayAccountDTO;
import org.gateway.infrastructure.DTO.GatewayUserDTO;
import org.gateway.infrastructure.requestEntities.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminApiImpl implements AdminApi {

    private final RestTemplate restTemplate;
    private final BankApiConfig bankApiConfig;
    
    @Override
    public void createClient(CreateUserRequest clientRequest, String password, Authentication auth){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getCredentials().toString());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateUserRequest> requestEntity = new HttpEntity<>(clientRequest, headers);
        restTemplate.postForEntity(bankApiConfig.getUrl() + "/api/users", requestEntity, CreateUserRequest.class);
    }

    @Override
    public List<GatewayUserDTO> getAllUsers(Authentication auth){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getCredentials().toString());
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = bankApiConfig.getUrl() + "/api/users";


        ResponseEntity<GatewayUserDTO[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                GatewayUserDTO[].class
        );

        if (response.getBody() == null) {
            return List.of();
        }
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<GatewayUserDTO> getAllUsersGenderFilter(String gender, Authentication auth){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getCredentials().toString());
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = bankApiConfig.getUrl() + "/api/users/{gender}/";
        ResponseEntity<GatewayUserDTO[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                GatewayUserDTO[].class,
                gender);
        if (response.getBody() == null) {
            return List.of();
        }
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<GatewayUserDTO> getAllUsersHairColorFilter(String haircolor, Authentication auth){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getCredentials().toString());
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = bankApiConfig.getUrl() + "/api/users/{haircolor}";
        ResponseEntity<GatewayUserDTO[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                GatewayUserDTO[].class,
                haircolor
        );

        if (response.getBody() == null) {
            return List.of();
        }
        return Arrays.asList(response.getBody());
    }

    @Override
    public GatewayUserDTO getUserById(long id, Authentication auth){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getCredentials().toString());
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<GatewayUserDTO> response = restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/users/{id}",
                HttpMethod.GET,
                entity,
                GatewayUserDTO.class,
                id
        );

        return response.getBody();
    }

    @Override
    public List<GatewayAccountDTO> getAllAccounts(Authentication auth){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getCredentials().toString());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<GatewayAccountDTO[]> response = restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/accounts",
                HttpMethod.GET,
                entity,
                GatewayAccountDTO[].class
        );
        if (response.getBody() == null) {
            return List.of();
        }
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<GatewayAccountDTO> getAllUserAccounts(long id, Authentication auth){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getCredentials().toString());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<GatewayAccountDTO[]> response = restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/users/{id}/accounts",
                HttpMethod.GET,
                entity,
                GatewayAccountDTO[].class,
                id
        );
        if (response.getBody() == null) {
            return List.of();
        }
        return Arrays.asList(response.getBody());
    }

    @Override
    public GatewayAccountDTO getAccountById(long accountId, Authentication auth){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getCredentials().toString());
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<GatewayAccountDTO> accountResponse = restTemplate.exchange(
                bankApiConfig.getUrl() + "/api/accounts/{accountId}",
                HttpMethod.GET,
                entity,
                GatewayAccountDTO.class,
                accountId
        );

        return accountResponse.getBody();
    }
}
