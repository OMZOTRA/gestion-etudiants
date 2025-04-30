package com.vde.gestionetudiants.modele;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCour;
    String courName;
    String enseignant;

    @ManyToMany(mappedBy = "cours")
    Set<Etudiant> etudiants;
}
