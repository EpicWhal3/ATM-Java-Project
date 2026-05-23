package org.bank.memory.DTO_entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bank.memory.entities.accounts.Account;
import org.bank.memory.entities.transactions.Transaction;
import org.bank.memory.entities.transactions.TransactionTypes;

import java.time.LocalDateTime;

@Data
public class AccountEventDto {
    private String eventType;
    private LocalDateTime eventTime = LocalDateTime.now();

    private Long accountId;
    private Double balance;
    private String ownerLogin;

    private FieldChanges changes;

    private TransactionSummary lastTransaction;

    public static AccountEventDto fromAccount(Account account, String eventType) {
        AccountEventDto dto = new AccountEventDto();
        dto.setEventType(eventType);
        dto.setAccountId(account.getId());
        dto.setBalance(account.getBalance());
        dto.setOwnerLogin(account.getOwner().getLogin());

        if (!account.getHistory().isEmpty()) {
            Transaction lastTx = account.getHistory().get(account.getHistory().size() - 1);
            dto.setLastTransaction(
                    new TransactionSummary(
                            lastTx.getId(),
                            lastTx.getType(),
                            lastTx.getAmount()
                    )
            );
        }

        return dto;
    }

    @Data
    @AllArgsConstructor
    public static class FieldChanges {
        private String changedField;
        private Object oldValue;
        private Object newValue;
    }

    @Data
    public static class TransactionSummary {
        private Long id;
        private TransactionTypes type;
        private Double amount;

        public TransactionSummary(Long id, TransactionTypes type, Double amount) {
            this.id = id;
            this.type = type;
            this.amount = amount;
        }
    }
}