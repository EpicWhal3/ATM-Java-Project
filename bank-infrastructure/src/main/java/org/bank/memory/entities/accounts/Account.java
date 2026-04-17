package org.bank.memory.entities.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.bank.memory.entities.transactions.Transaction;
import org.bank.memory.entities.transactions.TransactionStatus;
import org.bank.memory.entities.users.User;
import org.bank.memory.exceptions.AccountExceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания счета пользователя.
 */
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_login", referencedColumnName = "login")
    private User owner;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> history;


    /**
     * Конструктор для создания счета пользователя.
     *
     * @param owner логин пользователя
     */
    public Account(User owner) {
        this.owner = owner;
        this.balance = 0.0;
        this.history = new ArrayList<>();
    }

    public Account() {
    }

    /**
     * Метод для пополнения счета.
     *
     * @param amount сумма пополнения
     * @throws AccountExceptions исключение, если сумма депозита отрицательная
     */
    public void deposit(double amount) throws AccountExceptions {
        if (amount <= 0) {
            throw AccountExceptions.AmountIsNegativeException("Сумма депозита должна быть положительной.");
        }

        balance += amount;
    }

    /**
     * Метод для снятия средств со счета.
     *
     * @param amount сумма снятия
     * @throws AccountExceptions исключение, если сумма снятия отрицательная или недостаточно средств
     */
    public void withdraw(double amount) throws AccountExceptions {
        if (amount <= 0) {
            throw AccountExceptions.AmountIsNegativeException("Сумма должна быть положительной.");
        }

        if (amount > balance) {
            throw AccountExceptions.InsufficientFundsException("Недостаточно средств: доступно " + balance);
        }

        balance -= amount;
    }

    public void addTransaction(Transaction tx) {
        history.add(tx);
        tx.setStatus(TransactionStatus.SUCCESS);
    }
}
