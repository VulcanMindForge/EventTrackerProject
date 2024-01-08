package com.skilldistillery.tabletop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tabletop.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

}
