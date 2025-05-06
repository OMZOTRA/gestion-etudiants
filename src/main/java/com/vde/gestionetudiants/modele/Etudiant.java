package com.vde.gestionetudiants.modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
            inverseJoinColumns = @JoinColumn(name = "id_cour"))
            @JsonBackReference
            @JsonManagedReference
            private Set<Cours> cours = new HashSet<>();


    public void addCours(Cours cour) {
        cours.add(cour);
        cour.getEtudiants().add(this);

    }

    /* public void removeCours(Cours cour) {
        cours.remove(cour);
        cour.getEtudiants().remove(this);
    }*/
}
