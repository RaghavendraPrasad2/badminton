package com.advarra.badminton.model;

import jakarta.persistence.*;

@Entity
@Table(name = "match_scores", schema = "tournaments")
public class MatchScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tournament tournament;

    @ManyToOne
    private Match match;

    private String format;

    @ManyToOne
    private Player team1Player1;

    @ManyToOne
    private Player team1Player2;

    @ManyToOne
    private Player team2Player1;

    @ManyToOne
    private Player team2Player2;

    private int team1Points;

    private int team2Points;

    public MatchScore(Long id,
                      Tournament tournament,
                      Match match,
                      String format,
                      Player team1Player1,
                      Player team1Player2,
                      Player team2Player1,
                      Player team2Player2,
                      int team1Points,
                      int team2Points) {
        this.id = id;
        this.tournament = tournament;
        this.match = match;
        this.format = format;
        this.team1Player1 = team1Player1;
        this.team1Player2 = team1Player2;
        this.team2Player1 = team2Player1;
        this.team2Player2 = team2Player2;
        this.team1Points = team1Points;
        this.team2Points = team2Points;
    }

    public MatchScore() {
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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Player getTeam1Player1() {
        return team1Player1;
    }

    public void setTeam1Player1(Player team1Player1) {
        this.team1Player1 = team1Player1;
    }

    public Player getTeam1Player2() {
        return team1Player2;
    }

    public void setTeam1Player2(Player team1Player2) {
        this.team1Player2 = team1Player2;
    }

    public Player getTeam2Player1() {
        return team2Player1;
    }

    public void setTeam2Player1(Player team2Player1) {
        this.team2Player1 = team2Player1;
    }

    public Player getTeam2Player2() {
        return team2Player2;
    }

    public void setTeam2Player2(Player team2Player2) {
        this.team2Player2 = team2Player2;
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
        return "MatchScore{" +
                "id=" + id +
                ", tournament=" + tournament +
                ", match=" + match +
                ", format='" + format + '\'' +
                ", team1Player1=" + team1Player1 +
                ", team1Player2=" + team1Player2 +
                ", team2Player1=" + team2Player1 +
                ", team2Player2=" + team2Player2 +
                ", team1Points=" + team1Points +
                ", team2Points=" + team2Points +
                '}';
    }
}
