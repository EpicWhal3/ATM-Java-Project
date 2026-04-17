package org.gateway.infrastructure.requestEntities;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
