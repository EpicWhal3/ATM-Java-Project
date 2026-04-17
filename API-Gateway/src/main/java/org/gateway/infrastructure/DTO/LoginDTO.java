package org.gateway.infrastructure.DTO;

import org.gateway.infrastructure.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginDTO {
    private String token;
    private String login;
    private List<Role> role;
}
