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

class MeetingTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Meeting meeting;

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
		meeting = new Meeting();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		meeting = null;
	}

	@Test
	void test_Meeting_basic_mapping() {
		meeting = em.find(Meeting.class, 1);
		assertNotNull(meeting);
		assertEquals("Spelljammer", meeting.getCampaign());
	}
	
	@Test
	void test_Meeting_Player_OnetoOne_mapping () {
		meeting = em.find(Meeting.class, 1);
		assertNotNull(meeting.getStoryteller());
		assertEquals("Jacob", meeting.getStoryteller().getFirstName());
	}
	
	@Test
	void test_Meeting_Game_ManytoOne_mapping () {
		meeting = em.find(Meeting.class, 1);
		assertNotNull(meeting.getGame());
		assertEquals("Dungeons & Dragons", meeting.getGame().getName());
	}
	
	@Test
	void test_Meeting_Player_ManytoMany_mapping () {
		meeting = em.find(Meeting.class, 1);
		assertNotNull(meeting.getPlayers());
		assertTrue(meeting.getPlayers().size() > 0);
	}

}
