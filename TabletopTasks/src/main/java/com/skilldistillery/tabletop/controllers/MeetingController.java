package com.skilldistillery.tabletop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tabletop.entities.Meeting;
import com.skilldistillery.tabletop.services.MeetingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class MeetingController {

	@Autowired
	MeetingService meetingServ;
	
	@GetMapping("meetings")
	public List<Meeting> allMeetings() {
		return meetingServ.getAllMeetings();
	}
	
	@GetMapping("meetings/{id}")
	public Meeting meetingById(@PathVariable("id") int meetingId, HttpServletResponse resp) {
		Meeting meeting = meetingServ.findById(meetingId);
		if (meeting == null) {
			resp.setStatus(404);
		}
		return meeting;
	}
	
	@PostMapping("meetings")
	public Meeting createMeeting(@RequestBody Meeting meeting, HttpServletResponse resp, HttpServletRequest req) {
		try {
			Meeting newMeeting = meetingServ.createMeeting(meeting);
			resp.setStatus(201);
			resp.setHeader("Location", req.getRequestURL().append("/").append(newMeeting.getId()).toString());
			return newMeeting;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}
		return meeting;
	}
	
	@PutMapping("meetings/{meetingId}")
	public Meeting editMeeting(@PathVariable("meetingId") int meetingId, @RequestBody Meeting meeting, HttpServletResponse resp) {
		try {
			Meeting editMeeting = meetingServ.editMeeting(meetingId, meeting);
			if (editMeeting == null) {
				resp.setStatus(404);
			}
			return editMeeting;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			meeting = null;
		}
		return meeting;
	}
	
	@DeleteMapping("meetings/{meetingId}")
	public void removeMeeting(@PathVariable("meetingId") int meetingId, HttpServletResponse resp) {
		try {
			if (meetingServ.removeMeeting(meetingId)) {
				resp.setStatus(204);
			} else {
				resp.setStatus(404);
			} 
		} catch (Exception e) {
			resp.setStatus(400);
		}
	}
 }
