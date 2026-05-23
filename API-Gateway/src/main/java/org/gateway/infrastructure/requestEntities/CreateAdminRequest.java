package org.gateway.infrastructure.requestEntities;

import lombok.Data;

@Data
public class CreateAdminRequest {
    private String login;
    private String password;
}
