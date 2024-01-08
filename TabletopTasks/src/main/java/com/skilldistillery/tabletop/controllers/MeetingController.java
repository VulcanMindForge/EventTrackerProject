package com.skilldistillery.tabletop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tabletop.entities.Meeting;
import com.skilldistillery.tabletop.services.MeetingService;

@RestController
@RequestMapping("api")
public class MeetingController {

	@Autowired
	MeetingService meetingServ;
	
	@GetMapping("meetings")
	public List<Meeting> allMeetings() {
		return meetingServ.getAllMeetings();
	}
}
