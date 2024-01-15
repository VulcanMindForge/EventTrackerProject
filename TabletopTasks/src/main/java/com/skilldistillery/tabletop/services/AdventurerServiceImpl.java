package com.skilldistillery.tabletop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tabletop.entities.Adventurer;
import com.skilldistillery.tabletop.entities.Player;
import com.skilldistillery.tabletop.repositories.AdventurerRepository;
import com.skilldistillery.tabletop.repositories.PlayerRepository;

@Service
public class AdventurerServiceImpl implements AdventurerService {

	@Autowired
	AdventurerRepository adventRepo;
	
	@Autowired
	PlayerRepository playerRepo;
	
	@Override
	public List<Adventurer> getAllAdventurers () {
		return adventRepo.findAll();
	}

	@Override
	public Adventurer findById(int adventurerId) {
		return adventRepo.findById(adventurerId).get();
	}

	@Override
	public List<Adventurer> allAdventurersByIdPlayerId(int playerId) {
		return adventRepo.findByPlayer_Id(playerId);
	}

	@Override
	public Adventurer createAdventurer(Integer playerId, Adventurer adventurer) {
		if (playerRepo.findById(playerId).isPresent()) {
			adventurer.setPlayer(playerRepo.findById(playerId).get());
			return adventRepo.saveAndFlush(adventurer);			
		}
		return null;
	}

	@Override
	public Adventurer editAdventurer(int adventurerId, Adventurer adventurer) {
		Adventurer adventToEdit = adventRepo.findById(adventurerId).get();
		adventToEdit.setName(adventurer.getName());
		adventToEdit.setCharacterClass(adventurer.getCharacterClass());
		adventToEdit.setLevel(adventurer.getLevel());
		adventToEdit.setPlayer(adventurer.getPlayer());
		adventToEdit.setCampaign(adventurer.getCampaign());
		return adventRepo.saveAndFlush(adventToEdit);
	}

	@Override
	public Boolean removeAdventurer(int playerId, int adventurerId) {
		Player player = playerRepo.findById(playerId).get();
		player.removeAdventurer(adventRepo.findById(adventurerId).get());
		if (adventRepo.findById(adventurerId).isPresent()) {
			adventRepo.deleteById(adventurerId);
			return true;
		}
		return false;
	}
}
