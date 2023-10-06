package com.advarra.badminton.dtos.tournament_dtos.ongoing;

public class MatchDataDTO {
    private String team1Player1;
    private String team1Player2;
    private String team2Player1;
    private String team2Player2;
    private String tournamentId;
    private String matchId;
    private String gameFormat;

    public MatchDataDTO() {
    }

    public MatchDataDTO(String team1Player1,
                        String team1Player2,
                        String team2Player1,
                        String team2Player2,
                        String tournamentId,
                        String matchId,
                        String gameFormat) {
        this.team1Player1 = team1Player1;
        this.team1Player2 = team1Player2;
        this.team2Player1 = team2Player1;
        this.team2Player2 = team2Player2;
        this.tournamentId = tournamentId;
        this.matchId = matchId;
        this.gameFormat = gameFormat;
    }

    public String getTeam1Player1() {
        return team1Player1;
    }

    public void setTeam1Player1(String team1Player1) {
        this.team1Player1 = team1Player1;
    }

    public String getTeam1Player2() {
        return team1Player2;
    }

    public void setTeam1Player2(String team1Player2) {
        this.team1Player2 = team1Player2;
    }

    public String getTeam2Player1() {
        return team2Player1;
    }

    public void setTeam2Player1(String team2Player1) {
        this.team2Player1 = team2Player1;
    }

    public String getTeam2Player2() {
        return team2Player2;
    }

    public void setTeam2Player2(String team2Player2) {
        this.team2Player2 = team2Player2;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getGameFormat() {
        return gameFormat;
    }

    public void setGameFormat(String gameFormat) {
        this.gameFormat = gameFormat;
    }

    @Override
    public String toString() {
        return "MatchDataDTO{" +
                "team1Player1='" + team1Player1 + '\'' +
                ", team1Player2='" + team1Player2 + '\'' +
                ", team2Player1='" + team2Player1 + '\'' +
                ", team2Player2='" + team2Player2 + '\'' +
                ", tournamentId='" + tournamentId + '\'' +
                ", matchId='" + matchId + '\'' +
                ", gameFormat='" + gameFormat + '\'' +
                '}';
    }
}
