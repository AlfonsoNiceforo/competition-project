package com.alfonso_niceforo.player_service.payload;

public class PlayersUsage {
    private String name;
    private Double percentage;

    public PlayersUsage(String name, Double percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
