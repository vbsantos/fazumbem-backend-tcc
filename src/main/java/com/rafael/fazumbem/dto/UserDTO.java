package com.rafael.fazumbem.dto;

import com.rafael.fazumbem.models.Address;
import lombok.*;

@Getter
@Builder
public class UserDTO {

    private Long idUser;

    private String username;

    private String password;

    private String group;

    private String name;

    private String institutionName;

    private String identifier;

    private String telephone;

    private String url;

    private String openHours;

    private String facebook;

    private String instagram;

    private String description;

    private String bankAccount;

    private Address address;

    private String image;
}
