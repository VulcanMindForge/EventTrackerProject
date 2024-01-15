package com.skilldistillery.tabletop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tabletop.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	Player findByUsernameLike(String Username);
}
