package org.gateway.application.services;

import org.gateway.application.interfaces.ClientApi;
import org.gateway.infrastructure.DTO.GatewayAccountDTO;
import org.gateway.infrastructure.DTO.GatewayFriendsAccountsDTO;
import org.gateway.infrastructure.DTO.GatewayUserDTO;
import org.gateway.infrastructure.requestEntities.TransferRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServices {

    private final ClientApi clientApi;

    public GatewayUserDTO getSelf(Authentication auth) {
        return clientApi.getSelf(auth);
    }

    public List<GatewayAccountDTO> getMyAccounts(Authentication auth) {
        return clientApi.getMyAccounts(auth);
    }

    public GatewayAccountDTO getAccountById(long id, Authentication auth) {
        return clientApi.getAccountById(id, auth);
    }

    @Transactional
    public void addFriend(long id, Authentication auth) {
        clientApi.addFriend(id, auth);
    }

    @Transactional
    public void deleteFriend(long id, Authentication auth) {
        clientApi.removeFriend(id, auth);
    }

    @Transactional
    public void transfer(TransferRequest transferRequest, Authentication auth) {
        clientApi.transfer(transferRequest, auth);
    }


    @Transactional
    public void deposit(long id, double amount, Authentication auth) {
        Long userId = getSelf(auth).getId();

        Long ownerId = getAccountById(id, auth).getUserId();

        if (!userId.equals(ownerId)) {
            throw new IllegalArgumentException("You are not the owner of this account");
        }

        clientApi.deposit(id, amount, auth);
    }


    @Transactional
    public void withdraw(long id, double amount, Authentication auth) {
        Long userId = getSelf(auth).getId();
        Long ownerId = getAccountById(id, auth).getUserId();
        if (!userId.equals(ownerId)) {
            throw new IllegalArgumentException("You are not the owner of this account");
        }

        clientApi.withdraw(id, amount, auth);
    }

    public List<GatewayFriendsAccountsDTO> getFriendsAndAccounts(Authentication auth) {
        return clientApi.getFriendsAccounts(auth);
    }
}
