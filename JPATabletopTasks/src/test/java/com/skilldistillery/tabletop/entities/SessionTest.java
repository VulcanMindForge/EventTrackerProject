package com.skilldistillery.tabletop.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class SessionTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Session session;

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
		session = new Session();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		session = null;
	}

	@Test
	void test_Session_basic_mapping() {
		session = em.find(Session.class, 1);
		assertNotNull(session);
		assertEquals("Spelljammer", session.getCampaign());
	}

}
