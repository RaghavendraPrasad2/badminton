package com.advarra.badminton.dtos.tournament_dtos.recent;
import java.util.ArrayList;
import java.util.List;
public class RecentTournamentDTO2 {
    private String winnerTeamName;
    private List<String> winnerTeamNames = new ArrayList<>();
    private String runnerTeamName;
    private List<String> runnerTeamNames = new ArrayList<>();
    private List<GameDetailsClass> gameDetails = new ArrayList<>();
    private List<String> allTeamNames = new ArrayList<>();
    private List<Integer> teamsTotalPoints = new ArrayList<>();
    private List<Integer> teamPoints = new ArrayList<>();
    private String tournamentName;
    public RecentTournamentDTO2() {
    }
    public RecentTournamentDTO2(String winnerTeamName, List<String> winnerTeamNames, String runnerTeamName,
                                List<String> runnerTeamNames, List<GameDetailsClass> gameDetails, List<String> allTeamNames,
                                List<Integer> teamsTotalPoints, List<Integer> teamPoints, String tournamentName) {
        super();
        this.winnerTeamName = winnerTeamName;
        this.winnerTeamNames = winnerTeamNames;
        this.runnerTeamName = runnerTeamName;
        this.runnerTeamNames = runnerTeamNames;
        this.gameDetails = gameDetails;
        this.allTeamNames = allTeamNames;
        this.teamsTotalPoints = teamsTotalPoints;
        this.teamPoints = teamPoints;
        this.tournamentName = tournamentName;
    }
    public String getWinnerTeamName() {
        return winnerTeamName;
    }
    public void setWinnerTeamName(String winnerTeamName) {
        this.winnerTeamName = winnerTeamName;
    }
    public String getRunnerTeamName() {
        return runnerTeamName;
    }
    public void setRunnerTeamName(String runnerTeamName) {
        this.runnerTeamName = runnerTeamName;
    }
    public List<String> getWinnerTeamNames() {
        return winnerTeamNames;
    }
    public void setWinnerTeamNames(List<String> winnerTeamNames) {
        this.winnerTeamNames = winnerTeamNames;
    }
    public List<String> getRunnerTeamNames() {
        return runnerTeamNames;
    }
    public void setRunnerTeamNames(List<String> runnerTeamNames) {
        this.runnerTeamNames = runnerTeamNames;
    }
    public List<GameDetailsClass> getGameDetails() {
        return gameDetails;
    }
    public void setGameDetails(List<GameDetailsClass> gameDetails) {
        this.gameDetails = gameDetails;
    }
    public List<String> getAllTeamNames() {
        return allTeamNames;
    }
    public void setAllTeamNames(List<String> allTeamNames) {
        this.allTeamNames = allTeamNames;
    }
    public List<Integer> getTeamsTotalPoints() {
        return teamsTotalPoints;
    }
    public void setTeamsTotalPoints(List<Integer> teamsTotalPoints) {
        this.teamsTotalPoints = teamsTotalPoints;
    }
    public void addTeamNameToTeamNames(String name){
        this.allTeamNames.add(name);
    }
    public void addGameDetailsToGameDetails(GameDetailsClass gameDetails){
        this.gameDetails.add(gameDetails);
    }
    public void addWinnerTeamNames(String name){
        this.winnerTeamNames.add(name);
    }
    public void addRunnerTeamNames(String name){
        this.runnerTeamNames.add(name);
    }
    public List<Integer> getTeamPoints() {
        return teamPoints;
    }
    public void setTeamPoints(List<Integer> teamPoints) {
        this.teamPoints = teamPoints;
    }
    public String getTournamentName() {
        return tournamentName;
    }
    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    @Override
    public String toString() {
        return "RecentTournamentDTO2 [winnerTeamName=" + winnerTeamName + ", winnerTeamNames=" + winnerTeamNames
                + ", runnerTeamName=" + runnerTeamName + ", runnerTeamNames=" + runnerTeamNames + ", gameDetails="
                + gameDetails + ", allTeamNames=" + allTeamNames + ", teamsTotalPoints=" + teamsTotalPoints
                + ", teamPoints=" + teamPoints + ", tournamentName=" + tournamentName + "]";
    }
}