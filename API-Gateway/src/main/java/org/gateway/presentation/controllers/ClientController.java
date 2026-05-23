package org.gateway.presentation.controllers;

import org.gateway.application.services.ClientServices;
import org.gateway.infrastructure.requestEntities.TransferRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@PreAuthorize("hasRole('CLIENT')")
@RequiredArgsConstructor
public class ClientController {
    private final ClientServices clientService;

    @Operation(summary = "Получить информацию о себе")
    @ApiResponse(responseCode = "200", description = "Информация о пользователе получена")
    @GetMapping("/self")
    public ResponseEntity<?> getSelf(Authentication authentication) {
        return ResponseEntity.ok(clientService.getSelf(authentication));
    }

    @Operation(summary = "Получить все свои счета")
    @ApiResponse(responseCode = "200", description = "Счета получены")
    @GetMapping("/accounts")
    public ResponseEntity<?> getMyAccounts(Authentication authentication) {
        return ResponseEntity.ok(clientService.getMyAccounts(authentication));
    }

    @Operation(summary = "Получить информацию о счете по ID")
    @ApiResponse(responseCode = "200", description = "Информация о счете получена")
    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable long id, Authentication authentication) {
        return ResponseEntity.ok(clientService.getAccountById(id, authentication));
    }

    @Operation(summary = "Добавить друга")
    @ApiResponse(responseCode = "200", description = "Друг добавлен")
    @PostMapping("/add_friend/{friendId}")
    public ResponseEntity<?> addFriend(@PathVariable("friendId") long friendId, Authentication authentication) {
        clientService.addFriend(friendId, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить друга")
    @ApiResponse(responseCode = "200", description = "Друг удален")
    @DeleteMapping("/delete_friend/{friendId}")
    public ResponseEntity<?> deleteFriend(@PathVariable("friendId") long friendId, Authentication authentication) {
        clientService.deleteFriend(friendId, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Перевод средств")
    @ApiResponse(responseCode = "200", description = "Перевод выполнен")
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest transferRequest, Authentication authentication) {
        clientService.transfer(transferRequest, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Пополнить счет")
    @ApiResponse(responseCode = "200", description = "Счет пополнен")
    @PostMapping("/accounts/{id}/deposit")
    public ResponseEntity<?> deposit(@PathVariable("id") long id, @RequestParam("amount") double amount,
                                     Authentication authentication) {
        clientService.deposit(id, amount, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Снять средства со счета")
    @ApiResponse(responseCode = "200", description = "Средства сняты")
    @PostMapping("/accounts/{id}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable("id") long id, @RequestParam("amount") double amount,
                                       Authentication authentication) {
        clientService.withdraw(id, amount, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/friends-and-accounts")
    public ResponseEntity<?> getFriendsAndAccounts(Authentication authentication) {
        return ResponseEntity.ok(clientService.getFriendsAndAccounts(authentication));
    }
}
