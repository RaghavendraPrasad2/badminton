package com.advarra.badminton.repositories;

import com.advarra.badminton.model.PastTournament;
import com.advarra.badminton.model.Player;
import com.advarra.badminton.model.Tournament;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByTournament_Id(Long tournamentId);

    Player findByName(String name);
//
//	Player findByNameAndTournamentId(String name, Long tournamentId);

	Player findByNameAndTournament(String player, Tournament tournament);
}
