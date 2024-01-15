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

class AdventurerTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Adventurer adventurer;

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
		adventurer = new Adventurer();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		adventurer = null;
	}

	@Test
	void test_Adventurer_basic_mapping() {
		adventurer = em.find(Adventurer.class, 1);
		assertNotNull(adventurer);
		assertEquals("Uthjack", adventurer.getName());
	}
	
	@Test
	void test_Adventurer_Player_ManyToOne_mapping() {
		adventurer = em.find(Adventurer.class, 1);
		assertNotNull(adventurer.getPlayer());
		assertEquals("Jacob", adventurer.getPlayer().getFirstName());
	}

	@Test
	void test_Adventurer_Campaign_ManyToOne_mapping() {
		adventurer = em.find(Adventurer.class, 1);
		assertNotNull(adventurer.getCampaign());
		assertEquals("Jacob", adventurer.getCampaign().getStoryteller().getFirstName());
	}

}
