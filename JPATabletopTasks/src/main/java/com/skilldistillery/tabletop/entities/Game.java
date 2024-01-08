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
import jakarta.persistence.OneToMany;

@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private Double version;
	@Column(name = "image_url")
	private String imageURL;

	@OneToMany(mappedBy = "game")
	@JsonIgnore
	private List<Adventurer> adventurers;

	@OneToMany(mappedBy = "game")
	@JsonIgnore
	private List<Meeting> meetings;
	
	public Game() {
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
			if (meeting.getGame() != null) {
				meeting.getGame().removeMeeting(meeting);
			}
			meeting.setGame(this);
		}
	}

	public void removeMeeting(Meeting meeting) {
		if (meetings != null && meetings.contains(meeting)) {
			meetings.remove(meeting);
			meeting.setGame(null);
		}
	}

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
			if (adventurer.getGame() != null) {
				adventurer.getGame().removeAdventurer(adventurer);
			}
			adventurer.setGame(this);
		}
	}

	public void removeAdventurer(Adventurer adventurer) {
		if (adventurers != null && adventurers.contains(adventurer)) {
			adventurers.remove(adventurer);
			adventurer.setGame(null);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getVersion() {
		return version;
	}

	public void setVersion(Double version) {
		this.version = version;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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
		Game other = (Game) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", version=" + version + ", imageURL=" + imageURL
				+ ", adventurers=" + adventurers + "]";
	}

}
