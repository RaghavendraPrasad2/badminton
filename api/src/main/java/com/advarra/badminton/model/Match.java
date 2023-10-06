package com.advarra.badminton.model;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "matches", schema = "tournaments")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Group group;

    @ManyToOne
    private Team team1;

    @ManyToOne
    private Team team2;

    @ManyToOne
    private Tournament tournament;

    private Timestamp scheduledDate;

    public Match() {
    }

    public Match(Long id, Group group, Team team1, Team team2, Tournament tournament, Timestamp scheduledDate) {
        this.id = id;
        this.group = group;
        this.team1 = team1;
        this.team2 = team2;
        this.tournament = tournament;
        this.scheduledDate = scheduledDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Timestamp getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Timestamp scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", group=" + group +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", tournament=" + tournament +
                ", scheduledDate=" + scheduledDate +
                '}';
    }
}
