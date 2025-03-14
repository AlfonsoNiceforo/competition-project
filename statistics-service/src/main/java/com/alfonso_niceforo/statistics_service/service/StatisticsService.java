package com.alfonso_niceforo.statistics_service.service;

import com.alfonso_niceforo.statistics_service.DAO.StatisticsDAO;
import com.alfonso_niceforo.statistics_service.payload.Players;
import com.alfonso_niceforo.statistics_service.payload.Team;
import com.alfonso_niceforo.statistics_service.payload.TeamUsage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {
    private final StatisticsDAO statisticsDAO;

    public StatisticsService(StatisticsDAO statisticsDAO) {
        this.statisticsDAO = statisticsDAO;
    }

    public List<Team> getAllCharactersData() {
        return statisticsDAO.selectAllTeamData();
    }

    public TeamUsage getCharactersData(String playerName1, String playerName2) {
        return statisticsDAO.getTeamData(playerName1, playerName2);
    }

    public List<Team> getCharacterStatistics(String name) {
        return statisticsDAO.selectCharacterData(name);
    }
}
