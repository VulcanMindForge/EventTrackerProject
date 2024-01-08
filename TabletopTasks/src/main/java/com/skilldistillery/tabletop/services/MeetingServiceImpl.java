package com.skilldistillery.tabletop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tabletop.entities.Meeting;
import com.skilldistillery.tabletop.repositories.MeetingRepository;

@Service
public class MeetingServiceImpl implements MeetingService {

	@Autowired
	MeetingRepository meetingRepo;
	
	@Override
	public List<Meeting> getAllMeetings() {
		return meetingRepo.findAll();
	}
}
