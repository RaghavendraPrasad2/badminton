package com.advarra.badminton.dtos.tournament_dtos.upcoming;

public class Group2DTO {
    private String name;
    private String teams;
    private String tournamentId;

    public Group2DTO() {
    }

    public Group2DTO(String name, String teams, String tournamentId) {
        this.name = name;
        this.teams = teams;
        this.tournamentId = tournamentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public String toString() {
        return "Group2DTO{" +
                "name='" + name + '\'' +
                ", teams='" + teams + '\'' +
                ", tournamentId='" + tournamentId + '\'' +
                '}';
    }
}
