package com.skilldistillery.tabletop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tabletop.entities.Adventurer;
import com.skilldistillery.tabletop.services.AdventurerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class AdventurerController {

	@Autowired
	AdventurerService adventServ;

	@GetMapping("players/{playerId}/adventurers")
	public List<Adventurer> adventurersByPlayerId(@PathVariable("playerId") int playerId) {
		return adventServ.allAdventurersByIdPlayerId(playerId);
	}

	@PostMapping("players/{playerId}/adventurers")
	public Adventurer createAdventurer(@PathVariable("playerId") int playerId, @RequestBody Adventurer adventurer,
			HttpServletResponse resp, HttpServletRequest req) {
		try {
			Adventurer newAdvent = adventServ.createAdventurer(playerId, adventurer);
			resp.setStatus(201);
			resp.setHeader("Location", req.getRequestURL().append("/").append(newAdvent.getId()).toString());
			return newAdvent;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}
		return adventurer;
	}

	@PutMapping("players/{playerId}/adventurers")
	public Adventurer editAdventurer(@PathVariable("playerId") int playerId, @RequestBody Adventurer adventurer,
			HttpServletResponse resp) {
		try {
			Adventurer editedAdvent = adventServ.editAdventurer(adventurer.getId(), adventurer);
			if (editedAdvent == null) {
				resp.setStatus(404);
			}
			return editedAdvent;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			adventurer = null;
		}
		return adventurer;
	}

	@DeleteMapping("players/{playerId}/adventurers/{adventurerId}")
	public void removeAdventurer(@PathVariable("playerId") int playerId, @PathVariable("adventurerId") int adventurerId,
			HttpServletResponse resp) {
		try {
			if (adventServ.removeAdventurer(playerId, adventurerId)) {
				resp.setStatus(204);
			} else {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			resp.setStatus(400);
		}
	}
}
