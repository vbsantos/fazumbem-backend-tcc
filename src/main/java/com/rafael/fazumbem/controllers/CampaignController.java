package com.rafael.fazumbem.controllers;

import com.rafael.fazumbem.dto.CampaignDTO;
import com.rafael.fazumbem.dto.UserDTO;
import com.rafael.fazumbem.models.Campaign;
import com.rafael.fazumbem.models.User;
import com.rafael.fazumbem.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/campaign")
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepository;

    @PostMapping
    public Campaign postCampaign(@RequestBody Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @GetMapping("/all")
    public List<CampaignDTO> getCampains() {
        List<Campaign> campaignsList = campaignRepository.findAll();
        List<CampaignDTO> campaigns = new ArrayList<>();
        for (Campaign campaign:campaignsList) {
            UserDTO userDTO =  UserDTO.builder()
                    .idUser(campaign.getUser().getIdUser())
                    .username(campaign.getUser().getUsername())
                    .group(campaign.getUser().getGroup())
                    .name(campaign.getUser().getName())
                    .institutionName(campaign.getUser().getInstitutionName())
                    .identifier(campaign.getUser().getIdentifier())
                    .telephone(campaign.getUser().getTelephone())
                    .url(campaign.getUser().getUrl())
                    .openHours(campaign.getUser().getOpenHours())
                    .facebook(campaign.getUser().getFacebook())
                    .instagram(campaign.getUser().getInstagram())
                    .description(campaign.getUser().getDescription())
                    .bankAccount(campaign.getUser().getBankAccount())
                    .address(campaign.getUser().getAddress())
                    .image(campaign.getUser().getImage()).build();
            CampaignDTO campaignDTO = CampaignDTO.builder()
                    .idCampaign(campaign.getIdCampaign())
                    .title(campaign.getTitle())
                    .description(campaign.getDescription())
                    .externalLink(campaign.getExternalLink())
                    .user(userDTO)
                    .images(campaign.getImages()).build();
            campaigns.add(campaignDTO);
        }

        return campaigns;
    }

    @GetMapping("/by/{username}")
    public List<CampaignDTO> getCampainsByUsername(@PathVariable String username) {
        List<Campaign> campaignsList = campaignRepository.findByUsername(username);
        List<CampaignDTO> campaigns = new ArrayList<>();
        for (Campaign campaign:campaignsList) {
            UserDTO userDTO =  UserDTO.builder()
                    .idUser(campaign.getUser().getIdUser())
                    .username(campaign.getUser().getUsername())
                    .group(campaign.getUser().getGroup())
                    .name(campaign.getUser().getName())
                    .institutionName(campaign.getUser().getInstitutionName())
                    .identifier(campaign.getUser().getIdentifier())
                    .telephone(campaign.getUser().getTelephone())
                    .url(campaign.getUser().getUrl())
                    .openHours(campaign.getUser().getOpenHours())
                    .facebook(campaign.getUser().getFacebook())
                    .instagram(campaign.getUser().getInstagram())
                    .description(campaign.getUser().getDescription())
                    .bankAccount(campaign.getUser().getBankAccount())
                    .address(campaign.getUser().getAddress())
                    .image(campaign.getUser().getImage()).build();
            CampaignDTO campaignDTO = CampaignDTO.builder()
                    .idCampaign(campaign.getIdCampaign())
                    .title(campaign.getTitle())
                    .description(campaign.getDescription())
                    .externalLink(campaign.getExternalLink())
                    .user(userDTO)
                    .images(campaign.getImages()).build();
            campaigns.add(campaignDTO);
        }

        return campaigns;
    }

    @GetMapping("/id/{id}")
    public Optional<Campaign> getCampainsById(@PathVariable Long id) {
        return campaignRepository.findById(id);
    }

    @PutMapping
    public Campaign putCampaign(@RequestBody Campaign campaign) {
        Campaign u = campaignRepository.findById(campaign.getIdCampaign()).get();
        if(u == null) {
            return null;
        }
        return campaignRepository.save(campaign);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaign(@PathVariable Long id){
        campaignRepository.deleteById(id);
    }
}
