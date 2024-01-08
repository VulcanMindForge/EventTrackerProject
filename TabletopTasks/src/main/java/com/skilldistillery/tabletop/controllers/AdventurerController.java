package com.skilldistillery.tabletop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tabletop.entities.Adventurer;
import com.skilldistillery.tabletop.services.AdventurerService;

@RestController
@RequestMapping("api")
public class AdventurerController {

	@Autowired
	AdventurerService adventServ;
	
	@GetMapping("adventurers")
	public List<Adventurer> allAdventurers () {
		return adventServ.getAllAdventurers();
	}
}
