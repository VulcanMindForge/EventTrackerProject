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

@ManyToOne
@JoinColumn(name = "campaign_id")
private Campaign campaign;

public Meeting () {}

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

public Campaign getCampaign() {
	return campaign;
}

public void setCampaign(Campaign campaign) {
	this.campaign = campaign;
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
	return "Meeting [id=" + id + ", location=" + location + ", startTime=" + startTime + ", campaign=" + campaign;
}

}
