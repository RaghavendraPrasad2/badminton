package com.advarra.badminton.dtos.tournament_dtos.recent;

import java.util.ArrayList;
import java.util.List;

public class GameDetailsClass {
    private String team1Name;
    private String team2Name;
    private String gameFormat;
    private List<String> team1Players = new ArrayList<>();
    private List<String> team2Players = new ArrayList<>();
    private int team1Points;
    private int team2Points;
    public GameDetailsClass() {
    }
    public GameDetailsClass(String team1Name,
                            String team2Name,
                            List<String> team1Players,
                            List<String> team2Players,
                            String gameFormat,
                            int team1Points,
                            int team2Points) {
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.team1Players = team1Players;
        this.team2Players = team2Players;
        this.gameFormat = gameFormat;
        this.team1Points = team1Points;
        this.team2Points = team2Points;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getGameFormat() {
        return gameFormat;
    }

    public void setGameFormat(String gameFormat) {
        this.gameFormat = gameFormat;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public List<String> getTeam1Players() {
        return team1Players;
    }

    public void setTeam1Players(List<String> team1Players) {
        this.team1Players = team1Players;
    }

    public List<String> getTeam2Players() {
        return team2Players;
    }

    public void setTeam2Players(List<String> team2Players) {
        this.team2Players = team2Players;
    }

    public int getTeam1Points() {
        return team1Points;
    }

    public void setTeam1Points(int team1Points) {
        this.team1Points = team1Points;
    }

    public int getTeam2Points() {
        return team2Points;
    }

    public void setTeam2Points(int team2Points) {
        this.team2Points = team2Points;
    }

    @Override
    public String toString() {
        return "GameDetailsClass{" +
                "team1Name='" + team1Name + '\'' +
                ", team2Name='" + team2Name + '\'' +
                ", gameFormat='" + gameFormat + '\'' +
                ", team1Players=" + team1Players +
                ", team2Players=" + team2Players +
                ", team1Points=" + team1Points +
                ", team2Points=" + team2Points +
                '}';
    }
}
