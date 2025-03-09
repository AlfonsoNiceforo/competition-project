package com.alfonso_niceforo.leaderboard_service.controller;

import com.alfonso_niceforo.leaderboard_service.DAO.LeaderboardDAO;
import com.alfonso_niceforo.leaderboard_service.payload.GameEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeaderboardController {
    @Autowired
    private LeaderboardDAO leaderboardDao;

    @GetMapping
    public ResponseEntity<List<GameEvent>> getLeaderboard() {
        return ResponseEntity.ok(leaderboardDao.getLeaderboard());
    }
}
