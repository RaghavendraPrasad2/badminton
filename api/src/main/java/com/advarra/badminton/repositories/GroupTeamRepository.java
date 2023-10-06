package com.advarra.badminton.repositories;

import com.advarra.badminton.model.GroupTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupTeamRepository extends JpaRepository<GroupTeam, Long> {
    List<GroupTeam> findAllByGroupId(Long groupId);
}
