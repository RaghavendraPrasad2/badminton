package com.advarra.badminton.dtos;

import com.advarra.badminton.model.Tournament;
import jakarta.persistence.ManyToOne;

public class TeamDTO {
    private Long id;
    private TournamentDTO tournament;

    private String name;

    public TeamDTO() {
    }

    public TeamDTO(Long id, TournamentDTO tournament, String name) {
        this.id = id;
        this.tournament = tournament;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", tournament=" + tournament +
                ", name='" + name + '\'' +
                '}';
    }
}
