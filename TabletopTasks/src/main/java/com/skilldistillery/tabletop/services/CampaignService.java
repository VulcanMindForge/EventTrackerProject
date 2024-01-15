package com.skilldistillery.tabletop.services;

import java.util.List;

import com.skilldistillery.tabletop.entities.Campaign;

public interface CampaignService {

	List<Campaign> getAllCampaigns();
	Campaign findById(int campaignId);
	Campaign createCampaign(Campaign campaign);
	Campaign editCampaign(int campaignId, Campaign campaign);
	Boolean removeCampaign(int campaignId);
}
