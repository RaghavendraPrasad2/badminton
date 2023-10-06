package com.advarra.badminton.repositories;

import com.advarra.badminton.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByTournamentId(Long tournamentId);
    Team findByName(String name);
    Team findByNameAndTournamentId(String name, Long tournamentId);
}
