package com.skilldistillery.tabletop.services;

import java.util.List;

import com.skilldistillery.tabletop.entities.Adventurer;

public interface AdventurerService {

	List<Adventurer> getAllAdventurers();
	Adventurer findById(int adventurerId);
	Adventurer createAdventurer(Integer playerId, Adventurer adventurer);
	Adventurer editAdventurer(int adventurerId, Adventurer adventurer);
	List<Adventurer> allAdventurersByIdPlayerId(int playerId);
	Boolean removeAdventurer(int playerId, int adventurerId);

}
