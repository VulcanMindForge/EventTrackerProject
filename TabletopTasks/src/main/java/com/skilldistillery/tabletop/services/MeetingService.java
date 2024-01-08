package com.skilldistillery.tabletop.services;

import java.util.List;

import com.skilldistillery.tabletop.entities.Meeting;

public interface MeetingService {

	List<Meeting> getAllMeetings();
	Meeting findById(int meetingId);
	Meeting createMeeting(Meeting meeting);
	Meeting editMeeting(int meetingId, Meeting meeting);
	Boolean removeMeeting(int meetingId);
}
