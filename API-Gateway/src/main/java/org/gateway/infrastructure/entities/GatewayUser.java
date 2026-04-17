package org.gateway.infrastructure.entities;


import org.gateway.infrastructure.entities.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "gateway_users")
@Data
@NoArgsConstructor
public class GatewayUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private List<Role> role;

    public GatewayUser(String login, String encode, List<Role> admin) {
        this.login = login;
        this.password = encode;
        this.role = admin;
    }
}
