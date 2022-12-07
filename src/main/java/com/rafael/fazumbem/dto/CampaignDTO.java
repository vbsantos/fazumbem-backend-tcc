package com.rafael.fazumbem.dto;

import com.rafael.fazumbem.models.User;
import lombok.*;

import java.util.List;

@Getter
@Builder
public class CampaignDTO {

    private Long idCampaign;

    private String title;

    private String description;

    private String externalLink;

    private UserDTO user;

    private List<String> images;
}
