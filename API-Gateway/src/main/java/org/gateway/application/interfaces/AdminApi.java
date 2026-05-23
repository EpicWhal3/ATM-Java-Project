package org.gateway.application.interfaces;

import org.gateway.infrastructure.DTO.GatewayAccountDTO;
import org.gateway.infrastructure.DTO.GatewayUserDTO;
import org.gateway.infrastructure.requestEntities.CreateUserRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AdminApi {

    void createClient(CreateUserRequest clientRequest, String password, Authentication auth);

    List<GatewayUserDTO> getAllUsers(Authentication auth);

    List<GatewayUserDTO> getAllUsersGenderFilter(String gender, Authentication auth);

    List<GatewayUserDTO> getAllUsersHairColorFilter(String hairColor, Authentication auth);

    GatewayUserDTO getUserById(long id, Authentication auth);

    List<GatewayAccountDTO> getAllAccounts(Authentication auth);

    List<GatewayAccountDTO> getAllUserAccounts(long id, Authentication auth);

    GatewayAccountDTO getAccountById(long id, Authentication auth);
}
