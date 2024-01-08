package com.skilldistillery.tabletop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tabletop.entities.Adventurer;

public interface AdventurerRepository extends JpaRepository<Adventurer, Integer>{

}
