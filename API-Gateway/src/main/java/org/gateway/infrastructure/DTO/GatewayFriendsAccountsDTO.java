package org.gateway.infrastructure.DTO;

import lombok.Data;

import java.util.List;

@Data
public class GatewayFriendsAccountsDTO {
    private String name;
    private List<Long> friendsAccounts;

    public GatewayFriendsAccountsDTO(String name, List<Long> friendsAccounts) {
        this.name = name;
        this.friendsAccounts = friendsAccounts;
    }
}
