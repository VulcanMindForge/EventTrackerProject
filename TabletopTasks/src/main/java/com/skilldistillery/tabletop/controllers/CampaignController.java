package com.skilldistillery.tabletop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tabletop.entities.Campaign;
import com.skilldistillery.tabletop.services.CampaignService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin({"*", "http://localhost/"})
@RestController
@RequestMapping("api")
public class CampaignController {

	@Autowired
	CampaignService campaignServ;
	
	@GetMapping("campaigns")
	public List<Campaign> allCampaigns() {
		return campaignServ.getAllCampaigns();
	}
	
	@GetMapping("campaigns/{id}")
	public Campaign campaignById(@PathVariable("id") int campaignId, HttpServletResponse resp) {
		Campaign campaign = campaignServ.findById(campaignId);
		if (campaign == null) {
			resp.setStatus(404);
		}
		return campaign;
	}
	
	@PostMapping("campaigns")
	public Campaign createCampaign(@RequestBody Campaign campaign, HttpServletResponse resp, HttpServletRequest req) {
		try {
			Campaign newCampaign = campaignServ.createCampaign(campaign);
			resp.setStatus(201);
			resp.setHeader("Location", req.getRequestURL().append("/").append(newCampaign.getId()).toString());
			return newCampaign;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}
		return campaign;
	}
	
	@PutMapping("campaigns/{campaignId}")
	public Campaign editCampaign(@PathVariable("campaignId") int campaignId, @RequestBody Campaign campaign, HttpServletResponse resp) {
		try {
			Campaign editCampaign = campaignServ.editCampaign(campaignId, campaign);
			if (editCampaign == null) {
				resp.setStatus(404);
			}
			return editCampaign;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			campaign = null;
		}
		return campaign;
	}
	
	@DeleteMapping("campaigns/{campaignId}")
	public void removeCampaign(@PathVariable("campaignId") int campaignId, HttpServletResponse resp) {
		try {
			if (campaignServ.removeCampaign(campaignId)) {
				resp.setStatus(204);
			} else {
				resp.setStatus(404);
			} 
		} catch (Exception e) {
			resp.setStatus(400);
		}
	}
 }
