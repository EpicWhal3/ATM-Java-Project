package org.gateway.infrastructure.DTO;

import lombok.Data;

import java.util.List;

@Data
public class GatewayAccountDTO {
    private long id;
    private Double balance;
    private long userId;
    private List<GatewayTransactionDTO> transactions;
}
