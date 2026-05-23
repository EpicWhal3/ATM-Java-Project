package org.bank.memory.requestEntites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserRequest {
    private String login;
    private String name;
    private int age;
    private String gender;
    private String hairColor;
}
