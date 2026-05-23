package org.bank.memory.DTO_entities;

import lombok.Getter;
import lombok.Setter;
import org.bank.memory.entities.transactions.TransactionStatus;
import org.bank.memory.entities.transactions.TransactionTypes;

/**
 * Класс для создания DTO (Data Transfer Object) для транзакции.
 */
@Getter
@Setter
public class TransactionDTO {
    private Long id;
    private TransactionTypes type;
    private TransactionStatus status;
    private String description;
    private double amount;

    public TransactionDTO(Long id, TransactionTypes type, TransactionStatus status, String description, double amount) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.description = description;
        this.amount = amount;
    }
}
