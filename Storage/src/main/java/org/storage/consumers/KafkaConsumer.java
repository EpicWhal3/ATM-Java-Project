package org.storage.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.bank.memory.DTO_entities.AccountEventDto;
import org.bank.memory.DTO_entities.ClientEventDto;
import org.storage.entities.AccountEvent;
import org.storage.entities.ClientEvent;
import org.storage.repo.AccountEventRepository;
import org.storage.repo.ClientEventRepository;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final AccountEventRepository accountRepo;
    private final ClientEventRepository clientRepo;

    @KafkaListener(topics = "account-topic", groupId = "storage-group")
    public void listenAccountTopic(String message) throws Exception {
        AccountEventDto dto = objectMapper.readValue(message, AccountEventDto.class);
        accountRepo.save(AccountEvent.fromDTO(dto));
    }

    @KafkaListener(topics = "client-topic", groupId = "storage-group")
    public void listenClientTopic(String message) throws Exception {
        ClientEventDto dto = objectMapper.readValue(message, ClientEventDto.class);
        clientRepo.save(ClientEvent.fromDTO(dto));
    }
}

