package org.bank.memory.exceptions;

/**
 * Класс для создания исключений для счета пользователя.
 */
public class AccountExceptions extends Exception {

    private AccountExceptions(String message) {
        super(message);
    }

    public static AccountExceptions AmountIsNegativeException(String message) {
        return new AccountExceptions(message);
    }

    public static AccountExceptions InsufficientFundsException(String message) {
        return new AccountExceptions(message);
    }

    public static AccountExceptions AccountNotFoundException(String message) {
        return new AccountExceptions(message);
    }

    public static AccountExceptions NoAccounts(String message) {
        return new AccountExceptions(message);
    }

    public static AccountExceptions NoTransactionsException(String message) {
        return new AccountExceptions(message);
    }
}
