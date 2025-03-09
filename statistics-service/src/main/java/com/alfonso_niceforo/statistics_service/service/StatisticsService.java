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

    public TeamUsage getCharactersData(Players players) {
        return statisticsDAO.getTeamData(players);
    }

    public List<Team> getCharacterStatistics(String name) {
        return statisticsDAO.selectCharacterData(name);
    }
}
