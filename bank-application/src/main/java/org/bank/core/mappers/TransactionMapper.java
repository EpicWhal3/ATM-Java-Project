package org.bank.core.mappers;


import org.bank.memory.DTO_entities.TransactionDTO;
import org.bank.memory.entities.transactions.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDTO toTransactionDTO(Transaction transaction);
    List<TransactionDTO> toTransactionDTOs(List<Transaction> transactions);
}
