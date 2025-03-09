package com.alfonso_niceforo.statistics_service.controller;

import com.alfonso_niceforo.statistics_service.payload.Players;
import com.alfonso_niceforo.statistics_service.payload.Team;
import com.alfonso_niceforo.statistics_service.payload.TeamUsage;
import com.alfonso_niceforo.statistics_service.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllData() {
        List<Team> res = statisticsService.getAllCharactersData();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/team")
    public ResponseEntity<TeamUsage> getTeamData(@RequestBody Players players) {
        TeamUsage res = statisticsService.getCharactersData(players);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Team>> getCharacterData(@PathVariable String name) {
        List<Team> res =  statisticsService.getCharacterStatistics(name);
        return ResponseEntity.ok(res);
    }
}
