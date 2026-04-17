package org.gateway.infrastructure.DTO;

import org.gateway.infrastructure.entities.enums.TransactionStatus;
import org.gateway.infrastructure.entities.enums.TransactionTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GatewayTransactionDTO {
    private Long id;
    private TransactionTypes type;
    private TransactionStatus status;
    private String description;
    private double amount;

    public GatewayTransactionDTO(Long id, TransactionTypes type, TransactionStatus status, String description, double amount) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.description = description;
        this.amount = amount;
    }
}
