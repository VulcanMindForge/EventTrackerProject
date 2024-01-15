package com.skilldistillery.tabletop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tabletop.entities.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

}
