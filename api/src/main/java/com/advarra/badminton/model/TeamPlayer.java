package com.advarra.badminton.model;

import jakarta.persistence.*;

@Entity
@Table(name = "team_players", schema = "tournaments")
public class TeamPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Player player;

    public TeamPlayer() {
    }

    public TeamPlayer(Long id, Team team, Player player) {
        this.id = id;
        this.team = team;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "TeamPlayer{" +
                "id=" + id +
                ", team=" + team +
                ", player=" + player +
                '}';
    }
}
