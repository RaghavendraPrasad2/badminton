package com.advarra.badminton.model;

import jakarta.persistence.*;

@Entity
@Table(name = "groups", schema = "tournaments")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tournament tournament;

    private String name;

    public Group() {
    }

    public Group(Long id, Tournament tournament, String name) {
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

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
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
        return "Group{" +
                "id=" + id +
                ", tournament=" + tournament +
                ", name='" + name + '\'' +
                '}';
    }
}
