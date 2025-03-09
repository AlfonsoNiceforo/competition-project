package com.alfonso_niceforo.leaderboard_service.DAO;

import com.alfonso_niceforo.leaderboard_service.payload.GameEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaderboardDAO {
    private final JdbcTemplate jdbcTemplate;

    public LeaderboardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate =  jdbcTemplate;
    }

    public void insertGameStats(GameEvent gameEvent) {
        String sql = "INSERT INTO leaderboard (playerName1, playerName2, time) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, gameEvent.getPlayerName1(), gameEvent.getPlayerName2(), gameEvent.getTime());
    }

    public List<GameEvent> getLeaderboard() {
        String sql = "SELECT * FROM leaderboard ORDER BY time ASC";
        return jdbcTemplate.query(sql, (rs, rn) -> (
                new GameEvent(
                        rs.getString("playerName1"),
                        rs.getString("playerName2"),
                        rs.getInt("time")
                )
        ));
    }
}
