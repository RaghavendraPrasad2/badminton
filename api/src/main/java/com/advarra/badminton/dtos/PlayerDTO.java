package com.advarra.badminton.dtos;

import com.advarra.badminton.model.Tournament;
import jakarta.persistence.ManyToOne;

public class PlayerDTO {

    private Long id;
    private TournamentDTO tournament;
    private String name;

    private String gender;
    private Integer rank;

    public PlayerDTO() {
    }

    public PlayerDTO(Long id, TournamentDTO tournament, String name, String gender, Integer rank) {
        this.id = id;
        this.tournament = tournament;
        this.name = name;
        this.gender = gender;
        this.rank = rank;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", tournament=" + tournament +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", rank=" + rank +
                '}';
    }
}
