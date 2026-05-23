package org.bank.memory.exceptions;

import org.bank.memory.DTO_entities.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExceptions.class)
    public ResponseEntity<ErrorResponse> handleUserExceptions(UserExceptions ex, WebRequest request) {
        HttpStatus status = determinedHttpStatus(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(AccountExceptions.class)
    public ResponseEntity<ErrorResponse> handleAccountExceptions(AccountExceptions ex, WebRequest request) {
        HttpStatus status = determinedHttpStatus(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    private HttpStatus determinedHttpStatus(Exception ex) {
        if (ex instanceof UserExceptions) {
            return ex.getMessage().contains("не найден") ?
                    HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        } else if (ex instanceof AccountExceptions) {
            return ex.getMessage().contains("не найден") ?
                    HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }


}
