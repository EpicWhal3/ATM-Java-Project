package org.bank.memory.DTO_entities;


import lombok.Getter;
import lombok.Setter;

/**
 * Класс для создания DTO (Data Transfer Object) для аккаунта.
 */
@Getter
@Setter
public class AccountDTO {
    private Long id;
    private double balance;

    public AccountDTO(Long id, double balance) {
        this.id = id;
        this.balance = balance;
    }
}
