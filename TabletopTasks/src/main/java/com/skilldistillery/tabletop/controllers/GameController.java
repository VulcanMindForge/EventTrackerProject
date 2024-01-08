package com.skilldistillery.tabletop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tabletop.entities.Game;
import com.skilldistillery.tabletop.services.GameService;

@RestController
@RequestMapping("api")
public class GameController {

	@Autowired
	GameService gameServ;
	
	@GetMapping("games")
	public List<Game> allGames() {
		return gameServ.getAllGames();
	}
}
