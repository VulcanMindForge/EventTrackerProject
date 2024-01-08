package com.skilldistillery.tabletop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tabletop.entities.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

}
