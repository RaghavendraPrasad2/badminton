package com.advarra.badminton.repositories;

import com.advarra.badminton.model.GroupResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupResultsRepository extends JpaRepository<GroupResult, Long> {
    List<GroupResult> findAllByGroupId(Long groupId);
}
