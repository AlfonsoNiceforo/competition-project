package com.alfonso_niceforo.player_service.payload;

public class GameEvent {
    private String playerName1;
    private String playerName2;
    private Integer time;

    public String getPlayerName1() {
        return playerName1;
    }
    public void setPlayerName1(String playerName1) {
        this.playerName1 = playerName1;
    }
    public String getPlayerName2() {
        return playerName2;
    }
    public void setPlayerName2(String playerName2) {
        this.playerName2 = playerName2;
    }
    public Integer getTime() {
        return time;
    }
    public void setTime(Integer time) {
        this.time = time;
    }
}
