package com.advarra.badminton.repositories;

import com.advarra.badminton.model.TeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamPlayerRepository extends JpaRepository<TeamPlayer, Long> {
    List<TeamPlayer> findAllPlayerIdByTeamId(Long teamId);

}
