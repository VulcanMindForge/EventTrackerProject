package com.skilldistillery.tabletop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tabletop.entities.Game;
import com.skilldistillery.tabletop.services.GameService;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin({"*", "http://localhost/"})
@RestController
@RequestMapping("api")
public class GameController {

	@Autowired
	GameService gameServ;
	
	@GetMapping("games")
	public List<Game> allGames() {
		return gameServ.getAllGames();
	}

	@GetMapping("games/{id}")
	public Game gameById(@PathVariable("id") int gameId, HttpServletResponse resp) {
		Game game = gameServ.findById(gameId);
		if (game == null) {
			resp.setStatus(404);
		}
		return game;
	}
}
