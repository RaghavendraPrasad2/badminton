package com.advarra.badminton.dtos.tournament_dtos.upcoming;

import java.util.ArrayList;
import java.util.List;

public class UpcomingTournamentDTO {

    private List<InitialCreateGroups> teams = new ArrayList<>();

    private List<InitialCreateTeams> players = new ArrayList<>();

    private List<InitialCreateScheduleManual> groupsAndTeams = new ArrayList<>();

    public UpcomingTournamentDTO() {
    }

    public UpcomingTournamentDTO(List<InitialCreateGroups> teams,
                                 List<InitialCreateTeams> players,
                                 List<InitialCreateScheduleManual> groupsAndTeams) {
        this.teams = teams;
        this.players = players;
        this.groupsAndTeams = groupsAndTeams;
    }

    public List<InitialCreateGroups> getTeams() {
        return teams;
    }

    public void setTeams(List<InitialCreateGroups> teams) {
        this.teams = teams;
    }

    public List<InitialCreateTeams> getPlayers() {
        return players;
    }

    public void setPlayers(List<InitialCreateTeams> players) {
        this.players = players;
    }

    public List<InitialCreateScheduleManual> getGroupsAndTeams() {
        return groupsAndTeams;
    }

    public void setGroupsAndTeams(List<InitialCreateScheduleManual> groupsAndTeams) {
        this.groupsAndTeams = groupsAndTeams;
    }

    @Override
    public String toString() {
        return "UpcomingTournamentDTO{" +
                "teams=" + teams +
                ", players=" + players +
                ", groupsAndTeams=" + groupsAndTeams +
                '}';
    }

    public void addPlayer(InitialCreateTeams initialCreateTeams){
        this.players.add(initialCreateTeams);
    }

    public void addTeam(InitialCreateGroups initialCreateGroups){
        this.teams.add(initialCreateGroups);
    }

    public void addGroupTeam(InitialCreateScheduleManual initialCreateScheduleManual){
        this.groupsAndTeams.add(initialCreateScheduleManual);
    }


}
