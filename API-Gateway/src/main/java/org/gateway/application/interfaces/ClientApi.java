package org.gateway.application.interfaces;

import org.gateway.infrastructure.DTO.GatewayAccountDTO;
import org.gateway.infrastructure.DTO.GatewayFriendsAccountsDTO;
import org.gateway.infrastructure.DTO.GatewayUserDTO;
import org.gateway.infrastructure.requestEntities.TransferRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientApi {
    GatewayUserDTO getSelf(Authentication auth);

    List<GatewayAccountDTO> getMyAccounts(Authentication auth);

    GatewayAccountDTO getAccountById(Long id, Authentication auth);

    List<GatewayFriendsAccountsDTO> getFriendsAccounts(Authentication auth);

    void transfer(TransferRequest transferRequest, Authentication auth);

    void addFriend(Long friendId, Authentication auth);

    void removeFriend(Long friendId, Authentication auth);

    void deposit(Long id, double amount, Authentication auth);

    void withdraw(Long id, double amount, Authentication auth);
}
