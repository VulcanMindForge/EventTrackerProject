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
	private String description;
	@Column(name = "image_url")
	private String imageURL;
	
	@OneToMany(mappedBy = "game")
	@JsonIgnore
	private List<Campaign> campaigns;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public List<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
	
	public void addCampaign(Campaign campaign) {
		if (campaigns == null) {
			campaigns = new ArrayList<>();
		}
		if (!campaigns.contains(campaign)) {
			campaigns.add(campaign);
			if (campaign.getGame() != null) {
				campaign.getGame().removeCampaign(campaign);
			}
			campaign.setGame(this);
		}
	}

	public void removeCampaign(Campaign campaign) {
		if (campaigns != null && campaigns.contains(campaign)) {
			campaigns.remove(campaign);
			campaign.setGame(this);
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
		Game other = (Game) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", version=" + description + ", imageURL=" + imageURL + ", campaigns="
				+ campaigns + "]";
	}
	
	
}