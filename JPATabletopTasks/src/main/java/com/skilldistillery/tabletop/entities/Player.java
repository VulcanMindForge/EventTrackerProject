package com.skilldistillery.tabletop.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Player {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name = "first_name")
private String firstName;
@Column(name = "last_name")
private String lastName;
private String address;

@OneToMany(mappedBy = "player")
@JsonIgnore
private List<Adventurer> adventurers;

@ManyToMany(mappedBy = "players")
@JsonIgnore
private List<Meeting> meetings;

public Player() {}

public List<Adventurer> getAdventurers() {
	return adventurers;
}

public void setAdventurers(List<Adventurer> adventurers) {
	this.adventurers = adventurers;
}

public void addAdventurer(Adventurer adventurer) {
	if (adventurers == null) {
		adventurers = new ArrayList<>();
	}
	if (!adventurers.contains(adventurer)) {
		adventurers.add(adventurer);
		if (adventurer.getPlayer() != null) {
			adventurer.getPlayer().removeAdventurer(adventurer);
		}
		adventurer.setPlayer(this);
	}
}

public void removeAdventurer(Adventurer adventurer) {
	if (adventurers != null && adventurers.contains(adventurer)) {
		adventurers.remove(adventurer);
		adventurer.setPlayer(null);
	}
}

public List<Meeting> getMeetings() {
	return meetings;
}

public void setMeetings(List<Meeting> meetings) {
	this.meetings = meetings;
}

public void addMeeting(Meeting meeting) {
	if (meetings == null) {
		meetings = new ArrayList<>();
	}
	if (!meetings.contains(meeting)) {
		meetings.add(meeting);
		meeting.addPlayer(this);
	}
}

public void removeMeeting(Meeting meeting) {
	if (meetings != null && meetings.contains(meeting)) {
		meetings.remove(meeting);
		meeting.removePlayer(this);
	}
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

@Override
public int hashCode() {
	return Objects.hash(id);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Player other = (Player) obj;
	return id == other.id;
}

@Override
public String toString() {
	return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
			+ ", adventurers=" + adventurers + ", meetings=" + meetings + "]";
}


}
