package com.vde.gestionetudiants.modele;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name= "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String firstname;
    private String lastname;
    private String login;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns =@JoinColumn(name ="userId" ),
            inverseJoinColumns = @JoinColumn(name ="name"))
            private Set<Roles> roles;
}
