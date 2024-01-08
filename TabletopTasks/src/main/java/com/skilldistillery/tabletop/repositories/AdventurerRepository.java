package com.skilldistillery.tabletop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tabletop.entities.Adventurer;

public interface AdventurerRepository extends JpaRepository<Adventurer, Integer>{
	List<Adventurer> findByPlayer_Id (Integer playerId);
}
