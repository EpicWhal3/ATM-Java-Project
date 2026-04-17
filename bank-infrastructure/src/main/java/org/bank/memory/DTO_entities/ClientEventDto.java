package org.bank.memory.DTO_entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bank.memory.entities.accounts.Account;
import org.bank.memory.entities.users.HairColor;
import org.bank.memory.entities.users.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClientEventDto {
    private String eventType;
    private LocalDateTime eventTime = LocalDateTime.now();

    private Long userId;
    private String name;
    private Integer age;
    private String gender;
    private HairColor hairColor;

    private FieldChanges changes;
    private List<Long> accountIds;
    private List<String> friendLogins;

    public static ClientEventDto fromUser(User user, String eventType) {
        ClientEventDto dto = new ClientEventDto();
        dto.setEventType(eventType);
        dto.setUserId(user.getId());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setGender(user.getGender());
        dto.setHairColor(user.getHairColor());

        if (user.getAccounts() != null) {
            dto.setAccountIds(
                    user.getAccounts().stream()
                            .map(Account::getId)
                            .collect(Collectors.toList())
            );
        }

        if (user.getFriends() != null) {
            dto.setFriendLogins(
                    user.getFriends().stream()
                            .map(User::getLogin)
                            .collect(Collectors.toList())
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
}