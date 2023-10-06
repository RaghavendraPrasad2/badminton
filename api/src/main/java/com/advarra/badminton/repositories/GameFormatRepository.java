package com.advarra.badminton.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advarra.badminton.model.GameFormat;

public interface GameFormatRepository extends JpaRepository<GameFormat,Long> {

}
