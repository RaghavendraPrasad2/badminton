package com.advarra.badminton.dtos;

import jakarta.persistence.ManyToOne;

public class TeamPlayerDTO {
    private Long id;
    private TeamDTO team;
    private PlayerDTO player;

    public TeamPlayerDTO() {
    }

    public TeamPlayerDTO(Long id, TeamDTO team, PlayerDTO player) {
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

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "TeamPlayerDTO{" +
                "id=" + id +
                ", team=" + team +
                ", player=" + player +
                '}';
    }
}
