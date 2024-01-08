package com.skilldistillery.tabletop.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class GameTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Game game;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPATabletopTasks");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		game = new Game();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		game = null;
	}

	@Test
	void test_Game_basic_mapping() {
		game = em.find(Game.class, 1);
		assertNotNull(game);
		assertEquals("Dungeons & Dragons", game.getName());
	}
	
	@Test
	void test_Game_Adventurers_OneToMany() {
		game = em.find(Game.class, 1);
		assertNotNull(game.getAdventurers());
		assertTrue(game.getAdventurers().size() > 0);
	}

	@Test
	void test_Game_Meetings_OneToMany() {
		game = em.find(Game.class, 1);
		assertNotNull(game.getMeetings());
		assertTrue(game.getMeetings().size() > 0);
	}
}
