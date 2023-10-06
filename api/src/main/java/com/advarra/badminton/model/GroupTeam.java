package com.advarra.badminton.model;

import jakarta.persistence.*;

@Entity
@Table(name = "group_teams", schema = "tournaments")
public class GroupTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Group group;

    @ManyToOne
    private Team team;

    public GroupTeam() {
    }

    public GroupTeam(Long id, Group group, Team team) {
        this.id = id;
        this.group = group;
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "GroupTeam{" +
                "id=" + id +
                ", group=" + group +
                ", team=" + team +
                '}';
    }
}
