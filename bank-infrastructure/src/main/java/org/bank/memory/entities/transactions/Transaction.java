package org.bank.memory.entities.transactions;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.bank.memory.entities.accounts.Account;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionTypes type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @Column(nullable = false)
    private Double amount;

    private String description;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Transaction() {
    }

    public Transaction(TransactionTypes type, TransactionStatus status, Double amount, String description, Account account) {
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.description = description;
        this.account = account;
        this.timestamp = LocalDateTime.now();
    }
}
