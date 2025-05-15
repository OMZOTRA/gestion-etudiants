package com.vde.gestionetudiants.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name= "etudiants")
public class Etudiant {
    @Id
    @Column(name = "idetudiant")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "firstname")
    String firstName;

    @Column(name = "lastname")
    String lastName;
    String email;

    @ManyToMany()
    @JoinTable(
            name = "etudiants_cours",
            joinColumns = @JoinColumn(name = "idetudiant"),
            inverseJoinColumns = @JoinColumn(name = "id_cour"))
            Set<Cours> cours = new HashSet<>();

}
