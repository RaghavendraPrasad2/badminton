package com.advarra.badminton.services.implementations;
import com.advarra.badminton.dtos.tournament_dtos.TournamentDataDTO;
import com.advarra.badminton.dtos.tournament_dtos.ongoing.GameFormatDTO;
import com.advarra.badminton.dtos.tournament_dtos.ongoing.MatchDTO;
import com.advarra.badminton.dtos.tournament_dtos.ongoing.MatchGroupDTO;
import com.advarra.badminton.dtos.tournament_dtos.ongoing.MatchPlayerDTO;
import com.advarra.badminton.dtos.tournament_dtos.recent.GameDetailsClass;
import com.advarra.badminton.dtos.tournament_dtos.recent.RecentTournamentDTO;
import com.advarra.badminton.dtos.tournament_dtos.recent.RecentTournamentDTO2;
import com.advarra.badminton.dtos.tournament_dtos.upcoming.*;
import com.advarra.badminton.model.*;
import com.advarra.badminton.repositories.*;
import com.advarra.badminton.services.interfaces.TournamentData;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class TournamentDataImplementation implements TournamentData {
    @Autowired
    public PastTournamentRepository pastTournamentRepository;
    @Autowired
    public RegisterRepository registerRepository;
    @Autowired
    public TournamentRepository tournamentRepository;
    @Autowired
    public TeamRepository teamRepository;
    @Autowired
    public GroupRepository groupRepository;
    @Autowired
    public GroupResultsRepository groupResultsRepository;
    @Autowired
    public MatchScoresRepository matchScoresRepository;
    @Autowired
    public GroupTeamRepository groupTeamRepository;
    @Autowired
    public TeamPlayerRepository teamPlayerRepository;
    @Autowired
    public PlayerRepository playerRepository;
    @Autowired
    public MatchesRepository matchesRepository;
    @Autowired
    public GameFormatRepository gameFormatRepository;
    
    private PastTournament getTournamentRecord(Long id){
        Optional<PastTournament> pastTournamentOptional = pastTournamentRepository.findById(id);
        return pastTournamentOptional.orElse(null);
    }
    private List<Team> getAllTeamsOfTournament(Long tournamentId){
        return teamRepository.findAllByTournamentId(tournamentId);
    }
    private String giveTeamName(Long teamId){
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if(optionalTeam.isPresent()){
            return optionalTeam.get().getName();
        }
        return "No team found";
    }
    private List<Long> giveGroupIDs(Long tournamentId){
        List<Long> groupIds = new ArrayList<>();
        List<Group> groups = groupRepository.findAllByTournament_Id(tournamentId);
        for(Group group: groups){
            groupIds.add(group.getId());
        }
        return groupIds;
    }
    private boolean getTeamNameById(Long id, String name){
        return Objects.equals(giveTeamName(id), name);
    }
    private List<Integer> getTeamNetPoints(Long tournamentId, List<String> teamNames){
        List<Long> groupIds = giveGroupIDs(tournamentId);
        List<GroupResult> groupResults = new ArrayList<>();
        List<Integer> teamNetPoints = new ArrayList<>();
        for(Long id: groupIds){
            groupResults.addAll(groupResultsRepository.findAllByGroupId(id));
        }
        for (String teamName : teamNames) {
            for (GroupResult groupResult : groupResults) {
                if (getTeamNameById(groupResult.getTeam().getId(), teamName)) {
                    teamNetPoints.add(groupResult.getNetPoints());
                }
            }
        }
        return teamNetPoints;
    }
    private List<Integer> getTeamPoints(Long tournamentId, List<String> teamNames){
        List<Long> groupIds = giveGroupIDs(tournamentId);
        List<GroupResult> groupResults = new ArrayList<>();
        List<Integer> teamPoints = new ArrayList<>();
        for(Long id: groupIds){
            groupResults.addAll(groupResultsRepository.findAllByGroupId(id));
        }
        for (String teamName : teamNames) {
            for (GroupResult groupResult : groupResults) {
                if (getTeamNameById(groupResult.getTeam().getId(), teamName)) {
                    teamPoints.add(groupResult.getPoints());
                }
            }
        }
        return teamPoints;
    }

    //Get recent tournament data
    public ResponseEntity<RecentTournamentDTO2> getRecentData(Long id){
        RecentTournamentDTO2 recentTournamentDTO2 = new RecentTournamentDTO2();
        List<MatchScore> matchScores = matchScoresRepository.findAllByTournamentId(id);
        PastTournament pastTournament = getTournamentRecord(id);
        List<Team> teams = getAllTeamsOfTournament(id);
        List<TeamPlayer> winnerTeamPlayers = teamPlayerRepository.findAllPlayerIdByTeamId(pastTournament.getWinnerTeam().getId());
        List<TeamPlayer> runnerTeamPlayers = teamPlayerRepository.findAllPlayerIdByTeamId(pastTournament.getRunnerUpTeam().getId());
        recentTournamentDTO2.setWinnerTeamName(pastTournament.getWinnerTeam().getName());
        for(TeamPlayer teamPlayer: winnerTeamPlayers){
            recentTournamentDTO2.addWinnerTeamNames(teamPlayer.getPlayer().getName());
        }
        recentTournamentDTO2.setRunnerTeamName(pastTournament.getRunnerUpTeam().getName());
        for(TeamPlayer teamPlayer: runnerTeamPlayers){
            recentTournamentDTO2.addRunnerTeamNames(teamPlayer.getPlayer().getName());
        }
        for(Team team: teams){
            recentTournamentDTO2.addTeamNameToTeamNames(team.getName());
        }
        recentTournamentDTO2.setTeamsTotalPoints(getTeamNetPoints(id, recentTournamentDTO2.getAllTeamNames()));
        recentTournamentDTO2.setTeamPoints(getTeamPoints(id, recentTournamentDTO2.getAllTeamNames()));
        for(MatchScore matchScore: matchScores){
            GameDetailsClass gameDetailsClass = new GameDetailsClass();
            List<String> players1 = new ArrayList<>();
            List<String> players2 = new ArrayList<>();
            players1.add(matchScore.getTeam1Player1().getName());
            if(matchScore.getTeam1Player2() != null){
                players1.add(matchScore.getTeam1Player2().getName());
            }
            players2.add(matchScore.getTeam2Player1().getName());
            if(matchScore.getTeam2Player2() != null){
                players2.add(matchScore.getTeam2Player2().getName());
            }
            gameDetailsClass.setTeam1Name(matchScore.getMatch().getTeam1().getName());
            gameDetailsClass.setTeam2Name(matchScore.getMatch().getTeam2().getName());
            gameDetailsClass.setGameFormat(matchScore.getFormat());
            gameDetailsClass.setTeam1Players(players1);
            gameDetailsClass.setTeam2Players(players2);
            gameDetailsClass.setTeam1Points(matchScore.getTeam1Points());
            gameDetailsClass.setTeam2Points(matchScore.getTeam2Points());
            recentTournamentDTO2.addGameDetailsToGameDetails(gameDetailsClass);
            recentTournamentDTO2.setTournamentName(tournamentRepository.findById(id).get().getName());
        }
        return new ResponseEntity<>(recentTournamentDTO2, HttpStatus.OK);
    }

    private Timestamp convertToTimestamp(String dateTimeString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date parsedDate = dateFormat.parse(dateTimeString);
        return new Timestamp(parsedDate.getTime());
    }

    public ResponseEntity<Map<String, String>> addTournament(TournamentDataDTO tournamentDataDTO) throws ParseException {
        Tournament tournament = new Tournament();
        tournament.setName(tournamentDataDTO.getLeagueName());
        tournament.setStartTime(convertToTimestamp(tournamentDataDTO.getStartDate()));
        tournament.setEndTime(convertToTimestamp(tournamentDataDTO.getEndDate()));
        tournament.setDescription(tournamentDataDTO.getDescription());
        tournament.setUser(registerRepository.findByContactInfo(tournamentDataDTO.getConductorEmail()));

        Tournament tournament2 = tournamentRepository.save(tournament);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("id", tournament2.getId().toString());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    public ResponseEntity<List<InitialCreateGroups>> getTeamsOfUpcomingTournament(Long tournamentId){
        List<InitialCreateGroups> createGroupsList = new ArrayList<>();
        List<Team> teams = teamRepository.findAllByTournamentId(tournamentId);
        for(Team team: teams){
            InitialCreateGroups initialCreateGroups = new InitialCreateGroups();
            initialCreateGroups.setId(team.getId());
            initialCreateGroups.setName(team.getName());
            createGroupsList.add(initialCreateGroups);
        }
        return new ResponseEntity<>(createGroupsList, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> addPlayerToDatabase(Player2DTO player2DTO){
        Map<String, String> resultMap = new HashMap<>();
        Player player = new Player();
        player.setName(player2DTO.getPlayerName());
        System.out.println(player.getName());
        player.setGender(player2DTO.getPlayerGender());
        System.out.println(player.getGender());
        player.setRank(player2DTO.getRank());
        player.setTournament( tournamentRepository.findById(player2DTO.getTournamentId()).get());
        Player player2 = playerRepository.save(player);
        resultMap.put("player", player2.getName());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    public ResponseEntity<List<InitialCreateTeams>> getPlayersOfUpcomingTournament(Long tournamentId){
        List<InitialCreateTeams> createTeamsList = new ArrayList<>();
        List<Player> players = playerRepository.findAllByTournament_Id(tournamentId);
        for(Player player: players){
        	if(player.isSelected() == false) {
	            InitialCreateTeams initialCreateTeams = new InitialCreateTeams();
	            initialCreateTeams.setPlayerName(player.getName());
	            initialCreateTeams.setPlayerRank(player.getRank());
	            initialCreateTeams.setPlayerGender(player.getGender());
	            createTeamsList.add(initialCreateTeams);
        	}
        }
        return new ResponseEntity<>(createTeamsList, HttpStatus.OK);
    }

    public ResponseEntity<List<InitialCreateScheduleManual>> getGroupsAndTeamsOfUpcomingTournament(Long tournamentId){
        List<InitialCreateScheduleManual> initialCreateScheduleManuals = new ArrayList<>();
        List<Group> groups = groupRepository.findAllByTournament_Id(tournamentId);
        for(Group group: groups){
            List<GroupTeam> groupTeams = groupTeamRepository.findAllByGroupId(group.getId());
            String groupName = group.getName();
            List<String> teamNames = new ArrayList<>();
            for(GroupTeam groupTeam: groupTeams) {
                teamNames.add(groupTeam.getTeam().getName());
            }
            InitialCreateScheduleManual initialCreateScheduleManual = new InitialCreateScheduleManual();
            initialCreateScheduleManual.addGroup(groupName, teamNames);
            initialCreateScheduleManuals.add(initialCreateScheduleManual);
        }
        return new ResponseEntity<>(initialCreateScheduleManuals, HttpStatus.OK);
    }
    public ResponseEntity<Map<String, String>> addTeamToDatabase(Team2DTO team2DTO){
        Map<String, String> resultMap = new HashMap<>();
        Team team = new Team();
        team.setName(team2DTO.getName());
        team.setTournament(tournamentRepository.findById(Long.parseLong(team2DTO.getTournamentId())).get());
        Team team2 = teamRepository.save(team);
        Long tournamentId1 =  Long.parseLong(team2DTO.getTournamentId());
        System.out.println(tournamentId1);
        List<String> playerNames = List.of(team2DTO.getPlayers().split(", "));
        for(String player: playerNames){
            TeamPlayer teamPlayer = new TeamPlayer();
            teamPlayer.setTeam(team2);
            Player playerSelect = playerRepository.findByNameAndTournament(player,tournamentRepository.findById(Long.parseLong(team2DTO.getTournamentId())).get());
            System.out.println(playerSelect);
            playerSelect.setSelected(true);
            System.out.println("after selected"+playerSelect);
            teamPlayer.setPlayer(playerRepository.findByName(player));
            teamPlayerRepository.save(teamPlayer);
        }
        resultMap.put("Message", "Added successfully");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    public void addGroupsToDatabase(Group2DTO group2DTO){
        Group group = new Group();
        group.setName(group2DTO.getName());
        group.setTournament(tournamentRepository.findById(Long.parseLong(group2DTO.getTournamentId())).get());
        Group group2 = groupRepository.save(group);

        List<String> teamNames = List.of(group2DTO.getTeams().split(", "));
        for(String teamName: teamNames){
            GroupTeam groupTeam = new GroupTeam();
            groupTeam.setGroup(group2);
            groupTeam.setTeam(teamRepository.findByNameAndTournamentId(teamName, group2.getTournament().getId()));
            groupTeamRepository.save(groupTeam);
        }
    }

    public ResponseEntity<Map<String, String>> addGroupsListToDatabase(List<Group2DTO> group2DTOList){
        Map<String, String> resultMap = new HashMap<>();
        for(Group2DTO group2DTO: group2DTOList){
            addGroupsToDatabase(group2DTO);
        }
        resultMap.put("Message", "Groups added successfully");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> addMatchesToDatabase(ScheduleDTO scheduleDTO){
        Map<String, String> resultMap = new HashMap<>();
        List<Match> existingMatches = matchesRepository.findAllByTournamentId(Long.parseLong(scheduleDTO.getTournamentId()));
        if(existingMatches.size() != 0){
            if(Boolean.parseBoolean(scheduleDTO.getClearData())){
                matchesRepository.deleteAllByTournamentId(Long.parseLong(scheduleDTO.getTournamentId()));
                for(Map.Entry<String, List<String>> entry: scheduleDTO.getMatches().entrySet()){
                    List<String> matches = entry.getValue();
                    Tournament tournament = tournamentRepository.findById(Long.parseLong(scheduleDTO.getTournamentId())).get();
                    Group group =  groupRepository.findByTournamentIdAndName(
                            Long.parseLong(scheduleDTO.getTournamentId()),
                            entry.getKey()
                    );
                    System.out.println(1);
                    for(String matchUp: matches){
                        Match match = new Match();
                        List<String> teams = List.of(matchUp.split(" vs "));
                        match.setTournament(tournament);
                        match.setGroup(group);
                        match.setTeam1(teamRepository.findByNameAndTournamentId(
                                teams.get(0),
                                Long.parseLong(scheduleDTO.getTournamentId())
                        ));
                        System.out.println(2);
                        match.setTeam2(teamRepository.findByNameAndTournamentId(
                                teams.get(1),
                                Long.parseLong(scheduleDTO.getTournamentId())
                        ));
                        matchesRepository.save(match);
                    }
                    System.out.println(3);
                }
                resultMap.put("message", "Matches updated successfully");
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }else{
                resultMap.put("message", String.valueOf(existingMatches.size()));
                return new ResponseEntity<>(resultMap, HttpStatus.CONFLICT);
            }
        }
        else {
            for(Map.Entry<String, List<String>> entry: scheduleDTO.getMatches().entrySet()){
                List<String> matches = entry.getValue();
                Tournament tournament = tournamentRepository.findById(Long.parseLong(scheduleDTO.getTournamentId())).get();
                Group group =  groupRepository.findByTournamentIdAndName(
                        Long.parseLong(scheduleDTO.getTournamentId()),
                        entry.getKey()
                );
                for(String matchUp: matches){
                    Match match = new Match();
                    List<String> teams = List.of(matchUp.split(" vs "));
                    match.setTournament(tournament);
                    match.setGroup(group);
                    match.setTeam1(teamRepository.findByNameAndTournamentId(
                            teams.get(0),
                            Long.parseLong(scheduleDTO.getTournamentId())
                    ));
                    match.setTeam2(teamRepository.findByNameAndTournamentId(
                            teams.get(1),
                            Long.parseLong(scheduleDTO.getTournamentId())
                    ));
                    matchesRepository.save(match);
                }
            }
            resultMap.put("Message", "Matches added successfully");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
    }

    public ResponseEntity<MatchGroupDTO> getAllMatchGroups(Long tournamentId){
        MatchGroupDTO matchGroupDTO = new MatchGroupDTO();
        Map<String, List<MatchDTO>> matchEntries = new HashMap<>();
        List<Group> groups = groupRepository.findAllByTournament_Id(tournamentId);
        for(Group group: groups){
            List<Match> groupMatches = matchesRepository.findAllByTournamentIdAndGroupId(tournamentId ,group.getId());
            List<MatchDTO> matchDTOs = new ArrayList<>();
            for(Match match: groupMatches){
                MatchDTO matchDTO = new MatchDTO();
                matchDTO.setId(match.getId());
                matchDTO.setMatch(match.getTeam1().getName() + " vs " + match.getTeam2().getName());
                matchDTOs.add(matchDTO);
            }
            matchEntries.put(group.getName(), matchDTOs);
        }
        matchGroupDTO.setGroups(matchEntries);
        return new ResponseEntity<>(matchGroupDTO, HttpStatus.OK);
    }

    public ResponseEntity<MatchPlayerDTO> getAllPlayersForAMatch(String identifier){
        MatchPlayerDTO matchPlayerDTO = new MatchPlayerDTO();
        List<String> ids = List.of(identifier.split("-"));
        Long matchId = Long.parseLong(ids.get(0));
        Long tournamentId = Long.parseLong(ids.get(1));

        matchPlayerDTO.setMatchId(matchId);


        return new ResponseEntity<>(matchPlayerDTO, HttpStatus.OK);
    }
	public ResponseEntity<Map<String, String>> addScheduleToDatabase(GameFormatDTO gameFormatDTO) {
		 Map<String, String> response = new HashMap<>();

		    try {
		       
		    	List<String> gameFormats = Arrays.stream(gameFormatDTO.getGameFormat().split(","))
		                .map(String::trim)
		                .collect(Collectors.toList());
		        System.out.println(gameFormats);

		        GameFormat gameFormat = new GameFormat();
		        
		        gameFormat.setTournament(tournamentRepository.findById(gameFormatDTO.getTournamentId()).get());

		        if (gameFormats.contains("Men Singles")) {
		            gameFormat.setMenSingles(true);
		        }
		        if (gameFormats.contains("Women Singles")) {
		            gameFormat.setWomenSingles(true);
		        }
		        if (gameFormats.contains("Men Doubles")) {
		            gameFormat.setMenDoubles(true);
		        }
		        if (gameFormats.contains("Women Doubles")) {
		            gameFormat.setWomenDoubles(true);
		        }
		        if (gameFormats.contains("Mixed Doubles")) {
		            gameFormat.setMixedDoubles(true);
		        }

		        gameFormatRepository.save(gameFormat);
		        
		        response.put("message", "Game format added successfully");
		        return ResponseEntity.ok(response);
		    } catch (Exception e) {
		        response.put("error", "Error adding game format to the database");
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		    }
	}
	public ResponseEntity<MatchPlayerDTO> getPlayersOfMatches(String urlData)
	{
	    String[] elements = urlData.split("-");
	    
	    if (elements.length == 2)
	    {
	    	Long matchId = Long.parseLong(elements[0]);
	    	Long tournamentId = Long.parseLong(elements[1]);
	    	List<Long[]> teamIds = matchesRepository.findTeam1IdAndTeam2IdByIdAndTournamentId(matchId,tournamentId);
	    	List<TeamPlayer> team1PlayerIds = teamPlayerRepository.findAllPlayerIdByTeamId(teamIds.get(0)[0]);
	    	
	    	for(TeamPlayer tp : team1PlayerIds)
	    	{
	    		System.out.println(tp.getPlayer());
	    		System.out.println(tp.getTeam());
	    	}
	    	
    
	    }
	    else
	    {
	        
	    }
	    
	    return null;
	}
}