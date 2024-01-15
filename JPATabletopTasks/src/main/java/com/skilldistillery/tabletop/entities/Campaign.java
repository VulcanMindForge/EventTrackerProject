package com.skilldistillery.tabletop.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
public class Campaign {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
@OneToOne
@JoinColumn(name = "storyteller")
private Player storyteller;

@ManyToOne
@JoinColumn(name = "game_id")
private Game game;

@ManyToMany
@JoinTable(name = "campaign_player", joinColumns = @JoinColumn(name = "campaign_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
private List<Player> players;

public Campaign() {}
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

public Player getStoryteller() {
	return storyteller;
}

public void setStoryteller(Player storyteller) {
	this.storyteller = storyteller;
}

public List<Player> getPlayers() {
	return players;
}

public void setPlayers(List<Player> players) {
	this.players = players;
}

public Game getGame() {
	return game;
}

public void setGame(Game game) {
	this.game = game;
}

public void addPlayer(Player player) {
	if (players == null) {
		players = new ArrayList<>();
	}
	if (!players.contains(player)) {
		players.add(player);
		player.addCampaign(this);
	}
}

public void removePlayer(Player player) {
	if (players != null && players.contains(player)) {
		players.remove(player);
		player.removeCampaign(this);
	}
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
	Campaign other = (Campaign) obj;
	return id == other.id;
}

@Override
public String toString() {
	return "Campaign [id=" + id + ", name=" + name + ", storyteller=" + storyteller + ", game=" + game + ", players="
			+ players + "]";
}


}
