package com.skilldistillery.tabletop.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Session {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String location;
@Column(name = "start_time")
private LocalDateTime startTime;
private String campaign;

public Session () {}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
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
	Session other = (Session) obj;
	return Objects.equals(id, other.id);
}

@Override
public String toString() {
	return "Session [id=" + id + ", location=" + location + ", startTime=" + startTime + ", campaign=" + campaign + "]";
}


}
