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

	@Override
	public Meeting findById(int meetingId) {
		Meeting meeting;
		if (meetingRepo.findById(meetingId).isPresent()) {
			meeting = meetingRepo.findById(meetingId).get();
		} else {
			meeting = null;
		}
		return meeting;
	}

	@Override
	public Meeting createMeeting(Meeting meeting) {
		return meetingRepo.saveAndFlush(meeting);
	}

	@Override
	public Meeting editMeeting(int meetingId, Meeting meeting) {
		Meeting editedMeeting = meetingRepo.findById(meetingId).get();
		editedMeeting.setLocation(meeting.getLocation());
		editedMeeting.setStartTime(meeting.getStartTime());
		editedMeeting.setCampaign(meeting.getCampaign());
		editedMeeting.setStoryteller(meeting.getStoryteller());
		editedMeeting.setPlayers(meeting.getPlayers());
		return meetingRepo.saveAndFlush(editedMeeting);
	}

	@Override
	public Boolean removeMeeting(int meetingId) {
		if (meetingRepo.findById(meetingId).isPresent()) {
			meetingRepo.deleteById(meetingId);
			return true;
		}
		return false;
	}
	
	
}
