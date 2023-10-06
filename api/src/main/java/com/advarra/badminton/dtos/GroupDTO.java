package com.advarra.badminton.dtos;

import com.advarra.badminton.model.Tournament;
import jakarta.persistence.ManyToOne;

public class GroupDTO {
    private Long id;
    private TournamentDTO tournament;
    private String name;
    public GroupDTO() {
    }
    public GroupDTO(Long id, TournamentDTO tournament, String name) {
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
        return "GroupDTO{" +
                "id=" + id +
                ", tournament=" + tournament +
                ", name='" + name + '\'' +
                '}';
    }
}
