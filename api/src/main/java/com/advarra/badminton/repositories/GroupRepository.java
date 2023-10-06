package com.advarra.badminton.repositories;

import com.advarra.badminton.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAllByTournament_Id(Long tournamentId);

    Group findByTournamentIdAndName(Long tournamentId, String name);
}
