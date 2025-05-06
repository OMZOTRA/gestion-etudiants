package com.vde.gestionetudiants.modele;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_cour;
    @Column(name = "courname")
    String courname;
    @Column(name = "enseignant")
    String enseignant;

    @ManyToMany(mappedBy = "cours")
    @JsonBackReference
    @JsonManagedReference
    private Set<Etudiant> etudiants = new HashSet<>();

}
