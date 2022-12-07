package com.rafael.fazumbem.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idToken;

    private String token;

    private LocalDateTime created;

    private LocalDateTime expired;

    private LocalDateTime confirmed;

    /*
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser")
    private User user;

     */

    public Token(String token, LocalDateTime created, LocalDateTime expired) {
        this.token = token;
        this.created = created;
        this.expired = expired;
        //this.user = user;
    }
}
