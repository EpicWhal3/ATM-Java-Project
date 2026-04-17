package org.bank.core.mappers;

import org.bank.memory.DTO_entities.AccountDTO;
import org.bank.memory.entities.accounts.Account;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toAccountDTO(Account account);
    List<AccountDTO> toAccountDTOs(List<Account> accounts);
}
