package com.alfonso_niceforo.statistics_service.payload;

public class Team {
    private String playerName1;
    private String playerName2;
    private Integer timesTogether;
    private Double percentage;

    public Team() {}

    public Team(String playerName1, String playerName2, Integer timesTogether, Double percentage) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
        this.timesTogether = timesTogether;
        this.percentage = percentage;
    }

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

    public Integer getTimesTogether() {
        return timesTogether;
    }

    public void setTimesTogether(Integer timesTogether) {
        this.timesTogether = timesTogether;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
