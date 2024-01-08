package com.skilldistillery.tabletop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tabletop.entities.Player;
import com.skilldistillery.tabletop.services.PlayerService;

@RestController
@RequestMapping("api")
public class PlayerController {
@Autowired
PlayerService playerServ;

@GetMapping("players")
public List<Player> allPlayers() {
	return playerServ.getAllPlayers();
}
}
