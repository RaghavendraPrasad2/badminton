package com.advarra.badminton.repositories;

import com.advarra.badminton.model.MatchScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchScoresRepository extends JpaRepository<MatchScore, Long> {
    List<MatchScore> findAllByTournamentId(Long tournamentId);
}
