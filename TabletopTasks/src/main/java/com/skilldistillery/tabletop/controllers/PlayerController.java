package com.skilldistillery.tabletop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tabletop.entities.Player;
import com.skilldistillery.tabletop.services.PlayerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin({"*", "http://localhost/"})
@RestController
@RequestMapping("api")
public class PlayerController {
	
	@Autowired
	PlayerService playerServ;

	@GetMapping("players")
	public List<Player> allPlayers() {
		return playerServ.getAllPlayers();
	}

	@GetMapping("players/{id}")
	public Player playerById(@PathVariable("id") int playerId, HttpServletResponse resp) {
		Player player = playerServ.findById(playerId);
		if (player == null) {
			resp.setStatus(404);
		}
		return player;
	}
	
	@GetMapping("players/username/{username}")
	public Player playerById(@PathVariable("username") String username, HttpServletResponse resp) {
		Player player = playerServ.findByUsernameLike(username);
		if (player == null) {
			resp.setStatus(404);
		}
		return player;
	}

	@PostMapping("players")
	public Player createPlayer(@RequestBody Player player, HttpServletResponse resp, HttpServletRequest req) {
		try {
			Player newPlayer = playerServ.createPlayer(player);
			resp.setStatus(201);
			resp.setHeader("Location", req.getRequestURL().append("/").append(newPlayer.getId()).toString());
			return newPlayer;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}
		return player;
	}

	@PutMapping("players/{playerId}")
	public Player editPlayer(@PathVariable("playerId") int playerId, @RequestBody Player player,
			HttpServletResponse resp) {
		try {
			Player editPlayer = playerServ.editPlayer(playerId, player);
			if (editPlayer == null) {
				resp.setStatus(404);
			}
			return editPlayer;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			player = null;
		}
		return player;
	}

	@DeleteMapping("players/{playerId}")
	public void removePlayer(@PathVariable("playerId") int playerId, HttpServletResponse resp) {
		try {
			if (playerServ.removePlayer(playerId)) {
				resp.setStatus(204);
			} else {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			resp.setStatus(400);
		}
	}
}
