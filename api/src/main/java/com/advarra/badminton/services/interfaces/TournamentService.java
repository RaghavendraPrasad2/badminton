package com.advarra.badminton.services.interfaces;

import com.advarra.badminton.dtos.TournamentCategoriesDTO;
import org.springframework.http.ResponseEntity;

public interface TournamentService {

    public ResponseEntity<TournamentCategoriesDTO> getTournaments(String sessionId);
}
