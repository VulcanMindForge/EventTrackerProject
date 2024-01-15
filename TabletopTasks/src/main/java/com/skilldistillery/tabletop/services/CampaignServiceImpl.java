package com.skilldistillery.tabletop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tabletop.entities.Campaign;
import com.skilldistillery.tabletop.repositories.CampaignRepository;

@Service
public class CampaignServiceImpl implements CampaignService {

	@Autowired
	CampaignRepository campaignRepo;
	
	@Override
	public List<Campaign> getAllCampaigns() {
		return campaignRepo.findAll();
	}

	@Override
	public Campaign findById(int campaignId) {
		Campaign campaign;
		if (campaignRepo.findById(campaignId).isPresent()) {
			campaign = campaignRepo.findById(campaignId).get();
		} else {
			campaign = null;
		}
		return campaign;
	}

	@Override
	public Campaign createCampaign(Campaign campaign) {
		return campaignRepo.saveAndFlush(campaign);
	}

	@Override
	public Campaign editCampaign(int campaignId, Campaign campaign) {
		Campaign editedCampaign = campaignRepo.findById(campaignId).get();
		editedCampaign.setName(campaign.getName());
		editedCampaign.setStoryteller(campaign.getStoryteller());
		editedCampaign.setPlayers(campaign.getPlayers());
		return campaignRepo.saveAndFlush(editedCampaign);
	}

	@Override
	public Boolean removeCampaign(int campaignId) {
		if (campaignRepo.findById(campaignId).isPresent()) {
			campaignRepo.deleteById(campaignId);
			return true;
		}
		return false;
	}
	
	
}
