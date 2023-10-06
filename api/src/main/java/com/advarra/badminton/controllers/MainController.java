package com.advarra.badminton.controllers;


import com.advarra.badminton.dtos.LoginDTO;
import com.advarra.badminton.dtos.RegisterDTO;
import com.advarra.badminton.dtos.TournamentCategoriesDTO;
import com.advarra.badminton.dtos.tournament_dtos.TournamentDataDTO;
import com.advarra.badminton.dtos.tournament_dtos.ongoing.GameFormatDTO;
import com.advarra.badminton.dtos.tournament_dtos.ongoing.MatchDataDTO;
import com.advarra.badminton.dtos.tournament_dtos.ongoing.MatchGroupDTO;
import com.advarra.badminton.dtos.tournament_dtos.ongoing.MatchPlayerDTO;
import com.advarra.badminton.dtos.tournament_dtos.recent.RecentTournamentDTO2;
import com.advarra.badminton.dtos.tournament_dtos.upcoming.*;
import com.advarra.badminton.services.implementations.LoginServiceImplementation;
import com.advarra.badminton.services.implementations.RegisterServiceImplementation;
import com.advarra.badminton.services.implementations.TournamentDataImplementation;
import com.advarra.badminton.services.implementations.TournamentServiceImplementation;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping(path = "")
public class MainController {

    @Autowired
    public RegisterServiceImplementation registerServiceImplementation;

    @Autowired
    public LoginServiceImplementation loginServiceImplementation;

    @Autowired
    public TournamentServiceImplementation tournamentServiceImplementation;

    @Autowired
    public TournamentDataImplementation tournamentDataImplementation;


    @PostMapping(path = "/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDTO registerDTO){
        return registerServiceImplementation.addApplication(registerDTO);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) throws Exception {
        return loginServiceImplementation.userLogin(loginDTO);
    }

    @GetMapping(path = "/getLeagues")
    public ResponseEntity<TournamentCategoriesDTO> getTournaments(@RequestHeader("sessionId") String sessionId){
        return tournamentServiceImplementation.getTournaments(sessionId);
    }

    @GetMapping(path = "/getRecentTournamentData/{id}")
    public ResponseEntity<RecentTournamentDTO2> getRecentTournamentData(@RequestHeader("sessionId") String sessionId,
                                                                        @PathVariable String id)
    {
        return tournamentDataImplementation.getRecentData(Long.parseLong(id));
    }

    @PostMapping(path = "/addTournament")
    public ResponseEntity<Map<String, String>> addTournament(@RequestBody TournamentDataDTO tournamentDataDTO) throws ParseException {
        System.out.println(tournamentDataDTO.toString());
        return tournamentDataImplementation.addTournament(tournamentDataDTO);
    }

    @PostMapping(path = "/addPlayer")
    public ResponseEntity<Map<String, String>> addPlayer(@RequestBody Player2DTO player2DTO){
    	player2DTO.setSelected(false);	
        return tournamentDataImplementation.addPlayerToDatabase(player2DTO);
    }

    @GetMapping(path = "/getUpcomingTeamsData/{id}")
    public ResponseEntity<List<InitialCreateGroups>> getUpcomingTournamentTeamsData(@PathVariable String id){
        return tournamentDataImplementation.getTeamsOfUpcomingTournament(Long.parseLong(id));
    }

    @GetMapping(path = "/getUpcomingPlayersData/{id}")
    public ResponseEntity<List<InitialCreateTeams>> getUpcomingTournamentPlayersData(@PathVariable String id){
        return tournamentDataImplementation.getPlayersOfUpcomingTournament(Long.parseLong(id));
    }

    @GetMapping(path = "/getUpcomingGroupsAndTeamsData/{id}")
    public ResponseEntity<List<InitialCreateScheduleManual>> getUpcomingTournamentGroupsAndTeamsData(@PathVariable String id){
        return tournamentDataImplementation.getGroupsAndTeamsOfUpcomingTournament(Long.parseLong(id));
    }
    @PostMapping(path = "/addTeam")
    public ResponseEntity<Map<String, String>> addTeamToDatabase(@RequestBody Team2DTO team2DTO){
        return tournamentDataImplementation.addTeamToDatabase(team2DTO);
    }
    
    @PostMapping(path = "/addSchedule")
    public ResponseEntity<Map<String,String>> addScheduleToDatabase(@RequestBody GameFormatDTO gameFormatDTO){
    	return tournamentDataImplementation.addScheduleToDatabase(gameFormatDTO);
    }

    @PostMapping(path = "/addGroups")
    public ResponseEntity<Map<String, String>> addGroupToDatabase(@RequestBody List<Group2DTO> group2DTOList){
        return tournamentDataImplementation.addGroupsListToDatabase(group2DTOList);
    }

    @PostMapping(path = "/addMatches")
    public ResponseEntity<Map<String, String>> addMatchesToDatabase(@RequestBody ScheduleDTO scheduleDTO){
       return tournamentDataImplementation.addMatchesToDatabase(scheduleDTO);
    }

    //We can delete this
    @PostMapping(path = "/matchData")
    public ResponseEntity<Map<String, Long>> addMatchDataToDatabase(@RequestBody MatchDataDTO matchDataDTO){
        return null;
    }

    @GetMapping(path = "/getGroupMatches/{tournamentId}")
    public ResponseEntity<MatchGroupDTO> getMatchGroupsData(@PathVariable String tournamentId){
        return tournamentDataImplementation.getAllMatchGroups(Long.parseLong(tournamentId));
    }

    @GetMapping(path = "/getPlayersForMatches/{urlData}")
    public ResponseEntity<MatchPlayerDTO> getPlayersOfMatches(@PathVariable String urlData){
        return tournamentDataImplementation.getPlayersOfMatches(urlData);
    }
}
