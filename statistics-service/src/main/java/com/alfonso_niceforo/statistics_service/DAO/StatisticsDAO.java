package com.alfonso_niceforo.statistics_service.DAO;

import com.alfonso_niceforo.statistics_service.payload.Players;
import com.alfonso_niceforo.statistics_service.payload.Team;
import com.alfonso_niceforo.statistics_service.payload.TeamUsage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StatisticsDAO {
    private final JdbcTemplate jdbcTemplate;

    public StatisticsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Team> selectAllTeamData() {
        String sql = "SELECT playerName1, playerName2, timesTogether FROM statistics";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);

        Integer totale = mapList.stream()
                .map(mappa -> (Integer) mappa.get("timesTogether")).reduce(0, Integer::sum);

        List<Team> res = mapList.stream()
                .map(mappa -> new Team(
                                (String) mappa.get("playerName1"),
                                (String) mappa.get("playerName2"),
                                (Integer) mappa.get("timesTogether"),
                                BigDecimal.valueOf(((Integer) mappa.get("timesTogether")) * 1.0 / totale * 100)
                                        .setScale(2, RoundingMode.HALF_UP)
                                        .doubleValue()
                        )
                )
                .collect(Collectors.toList());

        return res;
    }


    public List<Team> selectCharacterData(String name) {
        String sql = "SELECT playerName1, playerName2, timesTogether, (timesTogether * 1.0 / total_usage.total) * 100 AS percentage "
                + "FROM statistics "
                + "JOIN (SELECT SUM(timesTogether) AS total FROM statistics WHERE playerName1 = ? OR playerName2 = ?) AS total_usage "
                + "ON 1=1 "
                + "WHERE playerName1 = ? OR playerName2 = ?";
        return jdbcTemplate.query(
                sql,
                (rs, rn) -> new Team(
                        rs.getString("playerName1"),
                        rs.getString("playerName2"),
                        rs.getInt("timesTogether"),
                        BigDecimal.valueOf(rs.getDouble("percentage"))
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()
                ),
                name, name, name, name);
    }


    public TeamUsage getTeamData(String playerName1, String playerName2) {
        List<String> lista = Arrays.asList(playerName1, playerName2);
        Collections.sort(lista);
        String sql = "SELECT * FROM statistics WHERE playerName1 = ? AND playerName2 = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (rs, rn) -> new TeamUsage(
                        rs.getString("playerName1"),
                        rs.getString("playerName2"),
                        rs.getInt("timesTogether")
                ),
                lista.get(0), lista.get(1)
        );
    }


    public void insertNewTeamOrUpdateUsage(Players players) {
        List<String> nomi = Arrays.asList(players.getPlayerName1(), players.getPlayerName2());
        Collections.sort(nomi);

	    /*
		String sql = "INSERT INTO statistiche (characterName1, characterName2, timesTogheter) "
				+ "VALUES (?, ?, 1) "
				+ "ON DUPLICATE KEY UPDATE timesTogheter=timesTogheter+1";
		*/

	    /*
		String sql = "INSERT INTO statistiche (characterName1, characterName2, timesTogheter) "
				+ "VALUES (?, ?, 1) "
				+ "ON CONFLICT (characterName1, characterName2) "
				+ "DO UPDATE SET timesTogheter = statistiche.timesTogheter + 1;";
		*/

        //Object[] args = new Object[] {nomi.get(0), nomi.get(1)};
        //jdbcTemplate.update(sql, args);

        int updatedRows = jdbcTemplate.update(
                "UPDATE statistics SET timesTogether = timesTogether + 1 WHERE playerName1 = ? AND playerName2 = ?",
                nomi.get(0), nomi.get(1)
        );

        if (updatedRows == 0) {
            jdbcTemplate.update(
                    "INSERT INTO statistics (playerName1, playerName2, timesTogether) VALUES (?, ?, 1)",
                    nomi.get(0), nomi.get(1)
            );
        }

    }
}
