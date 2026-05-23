package org.bank.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bank.memory.DTO_entities.AccountEventDto;
import org.bank.memory.DTO_entities.ClientEventDto;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendClientEvent(ClientEventDto event) throws Exception {
        String message = objectMapper.writeValueAsString(event);
        kafkaTemplate.send("client-topic", event.getUserId().toString(), message);
    }

    public void sendAccountEvent(AccountEventDto event) throws Exception {
        String message = objectMapper.writeValueAsString(event);
        kafkaTemplate.send("account-topic", event.getAccountId().toString(), message);
    }
}
