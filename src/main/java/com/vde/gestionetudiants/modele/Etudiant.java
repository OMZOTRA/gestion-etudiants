package com.vde.gestionetudiants.modele;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name= "etudiants")
public class Etudiant {
    @Id
    @Column(name = "idetudiant")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "firstname")
    String firstName;

    @Column(name = "lastname")
    String lastName;
    String email;

    @ManyToMany
    @JoinTable(
            name = "etudiants_cours",
            joinColumns = @JoinColumn(name = "idetudiant"),
            inverseJoinColumns = @JoinColumn(name = "idCour"))
            Set<Cours> cours;
}
