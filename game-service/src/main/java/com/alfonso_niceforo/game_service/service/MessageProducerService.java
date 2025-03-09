package com.alfonso_niceforo.game_service.service;

import com.alfonso_niceforo.game_service.payload.GameEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerService {

    private final KafkaTemplate<Void, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public MessageProducerService(KafkaTemplate<Void, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(GameEvent gameEvent) throws JsonProcessingException {
        String jsonMessage = objectMapper.writeValueAsString(gameEvent);
        kafkaTemplate.send("player-verified-topic", jsonMessage);
    }
}
