package org.gateway.presentation.controllers;

import org.gateway.application.services.AdminServices;
import org.gateway.infrastructure.requestEntities.CreateAdminRequest;
import org.gateway.infrastructure.requestEntities.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    private final AdminServices adminService;

    @Operation(summary = "Создать нового администратора")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Администратор успешно создан")
    })
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody CreateAdminRequest admin) {
        adminService.createAdmin(admin);
        return ResponseEntity.ok("Admin created successfully");
    }

    @Operation(summary = "Создать нового клиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Клиент успешно создан")
    })
    @PostMapping("/create-client")
    public ResponseEntity<?> createClient(@RequestBody CreateUserRequest client,
                                          @RequestParam("password") String password,
                                          Authentication auth) {
        adminService.createClient(client, password, auth);
        return ResponseEntity.ok("Client created successfully");
    }

    @Operation(summary = "Получить пользователей с фильтрами по полу и цвету волос")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список пользователей получен"),
            @ApiResponse(responseCode = "404", description = "Пользователи не найдены")
    })
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(Authentication auth) {
        return ResponseEntity.ok(adminService.getAllUsers(auth));
    }

    @Operation(summary = "Получить пользователей с фильтром по полу")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список пользователей получен")
    })
    @GetMapping("/all-users/{gender}")
    public ResponseEntity<?> getAllUsersFilteredByGender(@PathVariable("gender") String gender,
                                                         Authentication auth) {
        return ResponseEntity.ok(adminService.getAllUsersGenderFilter(gender, auth));
    }

    @Operation(summary = "Получить пользователей с фильтром по цвету волос")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список пользователей получен")
    })
    @GetMapping("/all-users/{hairColor}")
    public ResponseEntity<?> getAllUsersFilteredByHairColor(@PathVariable("hairColor") String hairColor,
                                                             Authentication auth) {
        return ResponseEntity.ok(adminService.getAllUsersHairColorFilter(hairColor, auth));
    }

    @Operation(summary = "Получить пользователя по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id, Authentication auth) {
        return ResponseEntity.ok(adminService.getUserById(id, auth));
    }

    @Operation(summary = "Получить всех пользователей")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список пользователей получен")
    })
    @GetMapping("/all-accounts")
    public ResponseEntity<?> getAllAccounts(Authentication authentication) {
        return ResponseEntity.ok(adminService.getAllAccounts(authentication));
    }

    @Operation(summary = "Получить все счета конкретного пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список аккаунтов пользователя получен")
    })
    @GetMapping("/users/{id}/accounts")
    public ResponseEntity<?> getUserAccounts(@PathVariable("id") long id, Authentication authentication) {
        return ResponseEntity.ok(adminService.getAllUserAccounts(id, authentication));
    }

    @Operation(summary = "Получить счёт по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Счёт найден"),
            @ApiResponse(responseCode = "404", description = "Счёт не найден")
    })
    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable("id") long id, Authentication authentication) {
        return ResponseEntity.ok(adminService.getAccountById(id, authentication));
    }
}
