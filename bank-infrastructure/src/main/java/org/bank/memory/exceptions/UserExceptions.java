package org.bank.memory.exceptions;

/**
 * Класс для создания исключений для пользователя.
 */
public class UserExceptions extends Exception {
    private UserExceptions(String message) {
        super(message);
    }

    public static UserExceptions UserNotFoundException(String message) {
        return new UserExceptions(message);
    }

    public static UserExceptions UserAlreadyExistsException(String message) {
        return new UserExceptions(message);
    }
}
