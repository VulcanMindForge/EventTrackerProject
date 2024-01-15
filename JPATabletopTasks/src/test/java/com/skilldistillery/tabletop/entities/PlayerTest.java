package com.skilldistillery.tabletop.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class PlayerTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Player player;

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
		player = new Player();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		player = null;
	}

	@Test
	void test_Player_basic_mapping() {
		player = em.find(Player.class, 1);
		assertNotNull(player);
		assertEquals("Jacob", player.getFirstName());
	}
	
	@Test
	void test_Adventurer_mapping_OneToMany_mapping() {
		player = em.find(Player.class, 1);
		assertNotNull(player);
		assertEquals("Jacob", player.getFirstName());
		assertNotNull(player.getAdventurers());
		assertTrue(player.getAdventurers().size() > 0);
	}
	
	@Test
	void test_Campaign_Player_ManyToMany_mapping() {
		player = em.find(Player.class, 1);
		assertNotNull(player);
		assertEquals("Jacob", player.getFirstName());
		assertNotNull(player.getCampaigns());
		assertTrue(player.getCampaigns().size() > 0);
	}

}
