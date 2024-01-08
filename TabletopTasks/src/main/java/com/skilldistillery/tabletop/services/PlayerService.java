package com.skilldistillery.tabletop.services;

import java.util.List;

import com.skilldistillery.tabletop.entities.Player;
import com.skilldistillery.tabletop.entities.Player;

public interface PlayerService {

	List<Player> getAllPlayers();
	Player findById(int playerId);
	Player createPlayer(Player player);
	Player editPlayer(int playerId, Player player);
	Boolean removePlayer(int playerId);

}
