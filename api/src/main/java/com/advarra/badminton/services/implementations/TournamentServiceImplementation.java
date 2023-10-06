package com.advarra.badminton.services.implementations;

import com.advarra.badminton.dtos.TournamentCategoriesDTO;
import com.advarra.badminton.dtos.TournamentDTO;
import com.advarra.badminton.dtos.UserDTO;
import com.advarra.badminton.model.Tournament;
import com.advarra.badminton.model.UserCredentials;
import com.advarra.badminton.repositories.RegisterRepository;
import com.advarra.badminton.repositories.TournamentRepository;
import com.advarra.badminton.services.interfaces.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TournamentServiceImplementation implements TournamentService {

    @Autowired
    public TournamentRepository tournamentRepository;

    @Autowired
    public RegisterRepository registerRepository;

    private UserDTO convertToUserDTO(UserCredentials userCredentials){
        return new UserDTO(userCredentials.getId(),
                userCredentials.getUserName(),
                userCredentials.getContactInfo(),
                userCredentials.getGender());
    }

    private TournamentDTO convertToTournamentDTO(Tournament tournament){
        TournamentDTO tournamentDTO = new TournamentDTO();
        tournamentDTO.setId(tournament.getId());
        tournamentDTO.setName(tournament.getName());
        tournamentDTO.setDescription(tournament.getDescription());
        tournamentDTO.setStartTime((tournament.getStartTime()));
        tournamentDTO.setEndTime(tournament.getEndTime());
        tournamentDTO.setUser(convertToUserDTO(tournament.getUser()));
        return tournamentDTO;
    }

    private List<TournamentDTO> convertToTournamentDTOList(List<Tournament> tournamentList){
        List<TournamentDTO> tournamentDTOS = new ArrayList<>();
        for(Tournament tournament: tournamentList){
            tournamentDTOS.add(convertToTournamentDTO(tournament));
        }
        return tournamentDTOS;
    }

    @Override
    public ResponseEntity<TournamentCategoriesDTO> getTournaments(String sessionId) {
        long currentTime = System.currentTimeMillis();
        UserCredentials user = registerRepository.findBySessionId(sessionId);
        if(user == null){
            return new ResponseEntity<>(new TournamentCategoriesDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }else{

            List<Tournament> ongoingTournaments = new ArrayList<>();
            List<Tournament> upcomingTournaments = new ArrayList<>();
            List<Tournament> recentTournaments = new ArrayList<>();

            List<Tournament> tournamentsList = tournamentRepository.findByUserId(user.getId());

            for (Tournament tournament : tournamentsList) {
                long startTime = tournament.getStartTime().getTime();
                long endTime = tournament.getEndTime().getTime();
                if (startTime <= currentTime && currentTime <= endTime) {
                    ongoingTournaments.add(tournament);
                } else if (startTime > currentTime) {
                    upcomingTournaments.add(tournament);
                } else if (endTime < currentTime) {
                    recentTournaments.add(tournament);
                }
            }
            TournamentCategoriesDTO categorizedTournaments = new TournamentCategoriesDTO();
            categorizedTournaments.setOnGoingTournaments(convertToTournamentDTOList(ongoingTournaments));
            categorizedTournaments.setUpcomingTournaments(convertToTournamentDTOList(upcomingTournaments));
            categorizedTournaments.setRecentTournaments(convertToTournamentDTOList(recentTournaments));
            System.out.println(categorizedTournaments);
            return ResponseEntity.ok(categorizedTournaments);
        }
    }
}
