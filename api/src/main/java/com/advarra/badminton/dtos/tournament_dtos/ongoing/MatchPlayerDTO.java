package com.advarra.badminton.dtos.tournament_dtos.ongoing;

import java.util.List;

public class MatchPlayerDTO {
    private Long matchId;
    private List<PlayerDTO> team1;
    private List<PlayerDTO> team2;
    private List<String> gameFormat;

    public MatchPlayerDTO() {
    }

    public MatchPlayerDTO(Long matchId, List<PlayerDTO> team1, List<PlayerDTO> team2, List<String> gameFormat) {
        this.matchId = matchId;
        this.team1 = team1;
        this.team2 = team2;
        this.gameFormat = gameFormat;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public List<PlayerDTO> getTeam1() {
        return team1;
    }

    public void setTeam1(List<PlayerDTO> team1) {
        this.team1 = team1;
    }

    public List<PlayerDTO> getTeam2() {
        return team2;
    }

    public void setTeam2(List<PlayerDTO> team2) {
        this.team2 = team2;
    }

    public List<String> getGameFormat() {
        return gameFormat;
    }

    public void setGameFormat(List<String> gameFormat) {
        this.gameFormat = gameFormat;
    }

    @Override
    public String toString() {
        return "MatchPlayerDTO{" +
                "matchId=" + matchId +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", gameFormat=" + gameFormat +
                '}';
    }
}
