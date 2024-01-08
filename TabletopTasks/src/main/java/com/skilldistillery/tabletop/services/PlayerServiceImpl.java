package com.skilldistillery.tabletop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tabletop.entities.Player;
import com.skilldistillery.tabletop.repositories.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {
@Autowired
PlayerRepository playerRepo;

@Override
public List<Player> getAllPlayers() {
	return playerRepo.findAll();
}
}
