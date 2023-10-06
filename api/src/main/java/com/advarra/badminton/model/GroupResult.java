package com.advarra.badminton.model;


import jakarta.persistence.*;

@Entity
@Table(name = "group_results", schema = "tournaments")
public class GroupResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Group group;

    @ManyToOne
    private Team team;

    private int points;
    private int netPoints;

    public GroupResult() {
    }

    public GroupResult(Long id, Group group, Team team, int points, int netPoints) {
        this.id = id;
        this.group = group;
        this.team = team;
        this.points = points;
        this.netPoints = netPoints;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNetPoints() {
        return netPoints;
    }

    public void setNetPoints(int netPoints) {
        this.netPoints = netPoints;
    }

    @Override
    public String toString() {
        return "GroupResult{" +
                "id=" + id +
                ", group=" + group +
                ", team=" + team +
                ", points=" + points +
                ", netPoints=" + netPoints +
                '}';
    }
}
