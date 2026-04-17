package org.bank.memory.DTO_entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bank.memory.entities.users.HairColor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private int age;
    private String gender;
    private HairColor hairColor;
    private List<Long> friends;
}