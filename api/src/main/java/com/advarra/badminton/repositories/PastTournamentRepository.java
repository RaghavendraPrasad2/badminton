package com.advarra.badminton.repositories;

import com.advarra.badminton.model.PastTournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PastTournamentRepository extends JpaRepository<PastTournament, Long> {
}
