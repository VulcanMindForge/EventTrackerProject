package com.skilldistillery.tabletop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tabletop.entities.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

}
