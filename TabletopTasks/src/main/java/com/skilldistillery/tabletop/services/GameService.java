package com.skilldistillery.tabletop.services;

import java.util.List;

import com.skilldistillery.tabletop.entities.Game;

public interface GameService {

	List<Game> getAllGames();
	Game findById(int gameId);
}
