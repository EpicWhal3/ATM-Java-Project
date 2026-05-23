package org.bank.core.mappers;

import org.bank.memory.DTO_entities.UserDTO;
import org.bank.memory.entities.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "friends", source = "friends", qualifiedByName = "usersToIds")
    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOs(List<User> users);

    @Named("usersToIds")
    default List<Long> toIds(List<User> users) {
        if (users.isEmpty()) {
            return List.of();
        }
        return users.stream().map(User::getId).collect(Collectors.toList());
    }
}