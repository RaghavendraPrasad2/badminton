package com.advarra.badminton.converters;

import com.advarra.badminton.dtos.*;
import com.advarra.badminton.model.*;

public class EntityToDTOConverter {
    public static UserDTO convertUserCredentialsToUserDTO(UserCredentials userCredentials){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userCredentials.getId());
        userDTO.setUserName(userCredentials.getUserName());
        userDTO.setGender(userCredentials.getGender());
        userDTO.setContactInfo(userCredentials.getContactInfo());
        return userDTO;
    }

    public static TournamentDTO convertTournamentToTournamentDTO(Tournament tournament){
        return new TournamentDTO(
          tournament.getId(),
          convertUserCredentialsToUserDTO(tournament.getUser()),
          tournament.getName(),
          tournament.getStartTime(),
          tournament.getEndTime(),
          tournament.getDescription()
        );
    }

    public static TeamDTO convertTeamToTeamDTO(Team team){
        return new TeamDTO(
                team.getId(),
                convertTournamentToTournamentDTO(team.getTournament()),
                team.getName()
        );
    }

    public static GroupDTO convertGroupToGroupDTO(Group group){
        return new GroupDTO(
                group.getId(),
                convertTournamentToTournamentDTO(group.getTournament()),
                group.getName()
        );
    }

    public static GroupTeamDTO convertGroupTeamToGroupTeamDTO(GroupTeam groupTeam){
        return new GroupTeamDTO(
                groupTeam.getId(),
                convertGroupToGroupDTO(groupTeam.getGroup()),
                convertTeamToTeamDTO(groupTeam.getTeam())
        );
    }

    public static PlayerDTO convertPlayerToPlayerDTO(Player player){
        return new PlayerDTO(
                player.getId(),
                convertTournamentToTournamentDTO(player.getTournament()),
                player.getName(),
                player.getGender(),
                player.getRank()
        );
    }

    public static TeamPlayerDTO convertTeamPlayerToTeamPlayerDTO(TeamPlayer teamPlayer){
        return new TeamPlayerDTO(
                teamPlayer.getId(),
                convertTeamToTeamDTO(teamPlayer.getTeam()),
                convertPlayerToPlayerDTO(teamPlayer.getPlayer())
        );
    }

    public static PastTournamentDTO convertPastTournamentToPastTournamentDTO(PastTournament pastTournament){
        return new PastTournamentDTO(
                pastTournament.getId(),
                convertTournamentToTournamentDTO(pastTournament.getTournament()),
                convertTeamToTeamDTO(pastTournament.getWinnerTeam()),
                convertTeamToTeamDTO(pastTournament.getRunnerUpTeam()),
                pastTournament.getDetails()
        );
    }

}
