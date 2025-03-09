package com.alfonso_niceforo.statistics_service.Kafka;

import com.alfonso_niceforo.statistics_service.DAO.StatisticsDAO;
import com.alfonso_niceforo.statistics_service.payload.GameEvent;
import com.alfonso_niceforo.statistics_service.payload.Players;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private StatisticsDAO statisticsDAO;
    private final ObjectMapper objectMapper;

    public KafkaListeners(StatisticsDAO statisticsDAO, ObjectMapper objectMapper) {
        this.statisticsDAO = statisticsDAO;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "player-verified-topic", groupId = "stats-group")
    public void receive(String json) throws JsonProcessingException {
        GameEvent gameEvent = objectMapper.readValue(json, GameEvent.class);
        Players players = new Players(gameEvent.getPlayerName1(), gameEvent.getPlayerName2());
        statisticsDAO.insertNewTeamOrUpdateUsage(players);
    }
}
