package com.advarra.badminton.dtos;

import com.advarra.badminton.model.Team;
import com.advarra.badminton.model.Tournament;
import jakarta.persistence.ManyToOne;

public class PastTournamentDTO {
    private Long id;
    private TournamentDTO tournament;
    private TeamDTO winnerTeam;
    private TeamDTO runnerUpTeam;
    private String details;

    public PastTournamentDTO() {
    }

    public PastTournamentDTO(Long id, TournamentDTO tournament, TeamDTO winnerTeam, TeamDTO runnerUpTeam, String details) {
        this.id = id;
        this.tournament = tournament;
        this.winnerTeam = winnerTeam;
        this.runnerUpTeam = runnerUpTeam;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TournamentDTO getTournament() {
        return tournament;
    }

    public void setTournament(TournamentDTO tournament) {
        this.tournament = tournament;
    }

    public TeamDTO getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(TeamDTO winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public TeamDTO getRunnerUpTeam() {
        return runnerUpTeam;
    }

    public void setRunnerUpTeam(TeamDTO runnerUpTeam) {
        this.runnerUpTeam = runnerUpTeam;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "PastTournamentDTO{" +
                "id=" + id +
                ", tournament=" + tournament +
                ", winnerTeam=" + winnerTeam +
                ", runnerUpTeam=" + runnerUpTeam +
                ", details='" + details + '\'' +
                '}';
    }

}
