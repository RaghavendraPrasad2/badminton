package com.advarra.badminton.model;

import jakarta.persistence.*;

@Entity
@Table(name = "past_tournaments", schema = "tournaments")
public class PastTournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tournament tournament;

    @ManyToOne
    private Team winnerTeam;

    @ManyToOne
    private Team runnerUpTeam;

    private String details;

    public PastTournament() {
    }

    public PastTournament(Long id, Tournament tournament, Team winnerTeam, Team runnerUpTeam, String details) {
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

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public Team getRunnerUpTeam() {
        return runnerUpTeam;
    }

    public void setRunnerUpTeam(Team runnerUpTeam) {
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
        return "PastTournament{" +
                "id=" + id +
                ", tournament=" + tournament +
                ", winnerTeam=" + winnerTeam +
                ", runnerUpTeam=" + runnerUpTeam +
                ", details='" + details + '\'' +
                '}';
    }
}
