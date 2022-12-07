package com.rafael.fazumbem.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    @ApiModelProperty(notes = "Address ID, auto increment", name = "idAddress")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAddress;

    @ApiModelProperty(notes = "Street", name = "street")
    private String street;

    @ApiModelProperty(notes = "Number", name = "number")
    private String number;

    @ApiModelProperty(notes = "Complement", name = "complement")
    private String complement;

    @ApiModelProperty(notes = "CEP", name = "cep")
    private String cep;

    @ApiModelProperty(notes = "City", name = "city")
    private String city;

    @ApiModelProperty(notes = "State", name = "state")
    private String state;

    @ApiModelProperty(notes = "District", name = "district")
    private String district;
}
