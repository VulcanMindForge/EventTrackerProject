package com.skilldistillery.tabletop.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Meeting {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String location;
@Column(name="start_time")
private LocalDateTime startTime;
private String campaign;
@OneToOne
@JoinColumn(name = "storyteller")
private Player storyteller;

@ManyToOne
@JoinColumn(name = "game_id")
private Game game;

@ManyToMany
@JoinTable(name = "meeting_player", joinColumns = @JoinColumn(name = "meeting_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
private List<Player> players;

public Meeting () {}

public List<Player> getPlayers() {
	return players;
}

public void setPlayers(List<Player> players) {
	this.players = players;
}

public void addPlayer(Player player) {
	if(players == null) {
		players = new ArrayList<>();
	}
	if (!players.contains(player)) {
		players.add(player);
		player.addMeeting(this);
	}
}

public void removePlayer(Player player) {
	if(players != null && players.contains(player)) {
		players.remove(player);
		player.removeMeeting(this);
	}
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public LocalDateTime getStartTime() {
	return startTime;
}

public void setStartTime(LocalDateTime startTime) {
	this.startTime = startTime;
}

public String getCampaign() {
	return campaign;
}

public void setCampaign(String campaign) {
	this.campaign = campaign;
}

public Player getStoryteller() {
	return storyteller;
}

public void setStoryteller(Player storyteller) {
	this.storyteller = storyteller;
}

public Game getGame() {
	return game;
}

public void setGame(Game game) {
	this.game = game;
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
	Meeting other = (Meeting) obj;
	return id == other.id;
}

@Override
public String toString() {
	return "Meeting [id=" + id + ", location=" + location + ", startTime=" + startTime + ", campaign=" + campaign
			+ ", storyteller=" + storyteller + ", game=" + game + "]";
}


}
