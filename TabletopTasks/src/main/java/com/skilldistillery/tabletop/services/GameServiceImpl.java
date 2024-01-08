package com.skilldistillery.tabletop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tabletop.entities.Game;
import com.skilldistillery.tabletop.repositories.GameRepository;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameRepository gameRepo;
	
	@Override
	public List<Game> getAllGames() {
		return gameRepo.findAll();
	}

	@Override
	public Game findById(int gameId) {
		Game game;
		if (gameRepo.findById(gameId).isPresent()) {
			game = gameRepo.findById(gameId).get();
		} else {
			game = null;
		}
		return game;
	}
	
}
