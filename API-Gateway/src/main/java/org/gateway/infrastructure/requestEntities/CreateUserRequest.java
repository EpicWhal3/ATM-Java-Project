package org.gateway.infrastructure.requestEntities;

import lombok.Data;


@Data
public class CreateUserRequest {
    private String login;
    private String name;
    private int age;
    private String gender;
    private String hairColor;
}
