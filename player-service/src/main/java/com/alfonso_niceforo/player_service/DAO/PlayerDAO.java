package com.alfonso_niceforo.player_service.DAO;

import com.alfonso_niceforo.player_service.payload.Players;
import com.alfonso_niceforo.player_service.payload.PlayersUsage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class PlayerDAO {
    private final JdbcTemplate jdbcTemplate;

    public PlayerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<PlayersUsage> getAllCharacters() {
        List<Map<String, Object>> dbRes = jdbcTemplate.queryForList("SELECT * FROM players");
        Integer totalPlayersUsage = dbRes.stream()
                .map(map -> (Integer) map.get("nTimeUsed"))
                .reduce(0, (count, toSum) -> Integer.sum(count, toSum));

        List<PlayersUsage> res = dbRes.stream()
                .map(map -> new PlayersUsage(
                                (String) map.get("name"),
                                BigDecimal.valueOf(((Integer) map.get("nTimeUsed")) * 1.0 / totalPlayersUsage * 100)
                                        .setScale(2, RoundingMode.HALF_UP)
                                        .doubleValue()
                        )
                )
                .collect(Collectors.toList());
        return res;
    }


    public Set<String> getAllCharacterNames() {
        String sql = "SELECT name FROM players";
        return new HashSet<>(jdbcTemplate.query(sql, (rs, rn) -> {
            return rs.getString("name");
        }));
    }


    public void incrementUsage(Players players) {
        String sql = "UPDATE players SET nTimeUsed = nTimeUsed+1 WHERE name = ? OR name = ?";
        jdbcTemplate.update(sql, players.getPlayerName1(), players.getPlayerName2());
    }
}
