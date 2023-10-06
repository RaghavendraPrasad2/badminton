package com.advarra.badminton.dtos;

import com.advarra.badminton.model.Group;
import com.advarra.badminton.model.Team;
import jakarta.persistence.ManyToOne;

public class GroupTeamDTO {
    private Long id;
    private GroupDTO group;
    private TeamDTO team;

    public GroupTeamDTO() {
    }

    public GroupTeamDTO(Long id, GroupDTO group, TeamDTO team) {
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

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "GroupTeamDTO{" +
                "id=" + id +
                ", group=" + group +
                ", team=" + team +
                '}';
    }
}
