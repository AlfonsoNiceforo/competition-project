package com.alfonso_niceforo.leaderboard_service.Kafka;

import com.alfonso_niceforo.leaderboard_service.DAO.LeaderboardDAO;
import com.alfonso_niceforo.leaderboard_service.payload.GameEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private final LeaderboardDAO leaderboardDAO;
    private final ObjectMapper objectMapper;

    public KafkaListeners(LeaderboardDAO leaderboardDAO, ObjectMapper objectMapper) {
        this.leaderboardDAO = leaderboardDAO;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "player-verified-topic", groupId = "leaderboard-group")
    public void insertNewRecord(String json) throws JsonProcessingException {
        GameEvent gameEvent = objectMapper.readValue(json, GameEvent.class);
        leaderboardDAO.insertGameStats(gameEvent);
    }
}
