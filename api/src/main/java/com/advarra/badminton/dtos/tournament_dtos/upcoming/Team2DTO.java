package com.advarra.badminton.dtos.tournament_dtos.upcoming;

public class Team2DTO {
    private String name;
    private String tournamentId;
    private String players;

    public Team2DTO() {
    }

    public Team2DTO(String name, String tournamentId, String players) {
        this.name = name;
        this.tournamentId = tournamentId;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team2DTO{" +
                "name='" + name + '\'' +
                ", tournamentId='" + tournamentId + '\'' +
                ", players='" + players + '\'' +
                '}';
    }
}
