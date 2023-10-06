package com.advarra.badminton.dtos.tournament_dtos.recent;

import com.advarra.badminton.converters.EntityToDTOConverter;
import com.advarra.badminton.dtos.*;
import com.advarra.badminton.model.*;

import java.util.ArrayList;
import java.util.List;

public class RecentTournamentDTO {
    private List<GroupDTO> groups = new ArrayList<>();
    private  List<GroupTeamDTO> groupTeams = new ArrayList<>();
    private List<TeamDTO> teams = new ArrayList<>();
    private List<TeamPlayerDTO> teamPlayers = new ArrayList<>();
    private List<PlayerDTO> players = new ArrayList<>();
    private PastTournamentDTO summaryData;

    public RecentTournamentDTO() {
    }
    public RecentTournamentDTO(List<GroupDTO> groups,
                               List<GroupTeamDTO> groupTeams,
                               List<TeamDTO> teams,
                               List<TeamPlayerDTO> teamPlayers,
                               List<PlayerDTO> players,
                               PastTournamentDTO summaryData)
    {
        this.groups = groups;
        this.groupTeams = groupTeams;
        this.teams = teams;
        this.teamPlayers = teamPlayers;
        this.players = players;
        this.summaryData = summaryData;
    }

    public PastTournamentDTO getSummaryData() {
        return summaryData;
    }

    public void setSummaryData(PastTournament summaryData) {
        this.summaryData = EntityToDTOConverter.convertPastTournamentToPastTournamentDTO(summaryData);
    }

    public List<GroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        List<GroupDTO> groups2 = new ArrayList<>();
        for(Group grp: groups){
            groups2.add(EntityToDTOConverter.convertGroupToGroupDTO(grp));
        }
        this.groups = groups2;
    }

    public List<GroupTeamDTO> getGroupTeams() {
        return groupTeams;
    }

    public void setGroupTeams(List<GroupTeam> groupTeams) {
        List<GroupTeamDTO> groupTeams2 = new ArrayList<>();
        for(GroupTeam groupTeam: groupTeams){
            groupTeams2.add(EntityToDTOConverter.convertGroupTeamToGroupTeamDTO(groupTeam));
        }
        this.groupTeams = groupTeams2;
    }

    public List<TeamDTO> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        List<TeamDTO> teams2 = new ArrayList<>();
        for(Team team: teams){
            teams2.add(EntityToDTOConverter.convertTeamToTeamDTO(team));
        }
        this.teams = teams2;
    }

    public List<TeamPlayerDTO> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(List<TeamPlayer> teamPlayers) {
        List<TeamPlayerDTO> teamPlayers2 = new ArrayList<>();
        for(TeamPlayer teamPlayer: teamPlayers){
            teamPlayers2.add(EntityToDTOConverter.convertTeamPlayerToTeamPlayerDTO(teamPlayer));
        }
        this.teamPlayers = teamPlayers2;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        List<PlayerDTO> players2 = new ArrayList<>();
        for(Player player: players){
            players2.add(EntityToDTOConverter.convertPlayerToPlayerDTO(player));
        }
        this.players = players2;
    }

    @Override
    public String toString() {
        return "RecentTournamentDTO{" +
                "groups=" + groups +
                ", groupTeams=" + groupTeams +
                ", teams=" + teams +
                ", teamPlayers=" + teamPlayers +
                ", players=" + players +
                ", summaryData=" + summaryData +
                '}';
    }

    public void addGroup(Group group){
        this.groups.add(EntityToDTOConverter.convertGroupToGroupDTO(group));
    }

    public void addGroupTeam(GroupTeam groupTeam){
        this.groupTeams.add(EntityToDTOConverter.convertGroupTeamToGroupTeamDTO(groupTeam));
    }

    public void addTeam(Team team){
        this.teams.add(EntityToDTOConverter.convertTeamToTeamDTO(team));
    }

    public void addTeamPlayer(TeamPlayer teamPlayer){
        this.teamPlayers.add(EntityToDTOConverter.convertTeamPlayerToTeamPlayerDTO(teamPlayer));
    }

    public void addPlayers(Player player){
        this.players.add(EntityToDTOConverter.convertPlayerToPlayerDTO(player));
    }
}
