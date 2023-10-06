package com.advarra.badminton.repositories;

import com.advarra.badminton.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MatchesRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByTournamentId(Long tournamentId);
    @Transactional
    void deleteAllByTournamentId(Long tournamentId);

    List<Match> findAllByTournamentIdAndGroupId(Long tournamentId, Long groupId);
    
    List<Match> findTeam1IdAndTeam2IdById(@Param("matchId") Long matchId);
    
    @Query("select m.team1.id, m.team2.id from Match m where m.id = :matchId and m.tournament.id = :tournamentId ")
	List<Long[]> findTeam1IdAndTeam2IdByIdAndTournamentId(Long matchId, Long tournamentId);
}
