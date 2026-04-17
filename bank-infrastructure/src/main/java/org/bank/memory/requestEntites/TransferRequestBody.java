package org.bank.memory.requestEntites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferRequestBody {
    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
}
