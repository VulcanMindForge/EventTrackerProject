package com.skilldistillery.tabletop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tabletop.entities.Adventurer;
import com.skilldistillery.tabletop.repositories.AdventurerRepository;

@Service
public class AdventurerServiceImpl implements AdventurerService {

	@Autowired
	AdventurerRepository adventRepo;
	
	@Override
	public List<Adventurer> getAllAdventurers () {
		return adventRepo.findAll();
	}
}
