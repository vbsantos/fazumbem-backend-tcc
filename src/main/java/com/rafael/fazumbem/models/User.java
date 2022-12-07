package com.rafael.fazumbem.models;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@user")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property = "@user", scope = User.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUser")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Long idUser;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "group")
    private String group;

    @Column(name = "name")
    private String name;

    @Column(name = "institutionName")
    private String institutionName;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "url")
    private String url;

    @Column(name = "openHours")
    private String openHours;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagram")
    private String instagram;

    @Lob
    @Type(type = "text")
    @Column(name = "description")
    private String description;

    @Lob
    @Type(type = "text")
    @Column(name = "bankAccount")
    private String bankAccount;

    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="id", scope = User.class,resolver = DedupingObjectIdResolver.class)
    //@JsonIdentityReference(alwaysAsId=true)
    //@JsonManagedReference
    private Set<Campaign> campaigns;

}
