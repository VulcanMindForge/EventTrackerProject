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

class CampaignTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Campaign campaign;

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
		campaign = new Campaign();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		campaign = null;
	}

	@Test
	void test_Game_basic_mapping() {
		campaign = em.find(Campaign.class, 1);
		assertNotNull(campaign);
		assertEquals("Spelljammer Bebop", campaign.getName());
	}
	
	@Test
	void test_Game_Players_ManyToMany_mapping() {
		campaign = em.find(Campaign.class, 1);
		assertNotNull(campaign);
		assertNotNull(campaign.getPlayers());
		assertTrue(campaign.getPlayers().size() > 0);
	}
}
