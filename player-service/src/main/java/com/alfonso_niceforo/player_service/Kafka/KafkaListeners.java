package com.alfonso_niceforo.player_service.Kafka;

import com.alfonso_niceforo.player_service.DAO.PlayerDAO;
import com.alfonso_niceforo.player_service.payload.GameEvent;
import com.alfonso_niceforo.player_service.payload.Players;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private final PlayerDAO playerDAO;
    private final ObjectMapper objectMapper;

    public KafkaListeners(PlayerDAO playerDAO, ObjectMapper objectMapper) {
        this.playerDAO = playerDAO;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "player-verified-topic", groupId = "player-group")
    public void updatePlayerUsage(String json) throws JsonProcessingException {
        GameEvent gameEvent = objectMapper.readValue(json, GameEvent.class);
        Players players = new Players(gameEvent.getPlayerName1(), gameEvent.getPlayerName2());
        playerDAO.incrementUsage(players);
    }
}
