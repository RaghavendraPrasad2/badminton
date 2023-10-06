package com.advarra.badminton.dtos.tournament_dtos.upcoming;

public class InitialCreateTeams {
    private String playerName;
    private int playerRank;
    private String playerGender;
    public InitialCreateTeams() {
    }
    public InitialCreateTeams(String playerName, int playerRank, String playerGender) {
        this.playerName = playerName;
        this.playerRank = playerRank;
        this.playerGender = playerGender;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(int playerRank) {
        this.playerRank = playerRank;
    }

    public String getPlayerGender() {
        return playerGender;
    }

    public void setPlayerGender(String playerGender) {
        this.playerGender = playerGender;
    }
    @Override
    public String toString() {
        return "InitialCreateTeams{" +
                "playerName='" + playerName + '\'' +
                ", playerRank=" + playerRank +
                ", playerGender='" + playerGender + '\'' +
                '}';
    }
}
