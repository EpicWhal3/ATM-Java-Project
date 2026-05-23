package org.gateway.infrastructure.requestEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferRequest {
    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
}
