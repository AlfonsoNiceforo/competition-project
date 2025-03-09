package com.alfonso_niceforo.statistics_service.payload;

public class TeamUsage {
    private String playerName1;
    private String playerName2;
    private Integer nTimeUsed;

    public TeamUsage() {}

    public TeamUsage(String characterName1, String characterName2, Integer nTimeUsed) {
        this.playerName1 = characterName1;
        this.playerName2 = characterName2;
        this.nTimeUsed = nTimeUsed;
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

    public Integer getnTimeUsed() {
        return nTimeUsed;
    }

    public void setnTimeUsed(Integer nTimeUsed) {
        this.nTimeUsed = nTimeUsed;
    }
}
