package com.skilldistillery.tabletop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tabletop.entities.Player;
import com.skilldistillery.tabletop.repositories.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {
@Autowired
PlayerRepository playerRepo;

@Override
public List<Player> getAllPlayers() {
	return playerRepo.findAll();
}

@Override
public Player findById(int playerId) {
	Player player;
	if (playerRepo.findById(playerId).isPresent()) {
		player = playerRepo.findById(playerId).get();
	} else {
		player = null;
	}
	return player;
}

@Override
public Player createPlayer(Player player) {
	return playerRepo.saveAndFlush(player);
}

@Override
public Player editPlayer(int playerId, Player player) {
	Player editedPlayer = playerRepo.findById(playerId).get();
	editedPlayer.setFirstName(player.getFirstName());
	editedPlayer.setLastName(player.getLastName());
	editedPlayer.setAddress(player.getAddress());
	editedPlayer.setAdventurers(player.getAdventurers());
	return playerRepo.saveAndFlush(editedPlayer);
}

@Override
public Boolean removePlayer(int playerId) {
	if (playerRepo.findById(playerId).isPresent()) {
		playerRepo.deleteById(playerId);
		return true;
	}
	return false;
}

@Override
public Player findByUsernameLike(String username) {
	Player player;
//	username =  username + "%";
	if (playerRepo.findByUsernameLike(username) != null) {
		player = playerRepo.findByUsernameLike(username);
	} else {
		player = null;
	}
	return player;
}




}
