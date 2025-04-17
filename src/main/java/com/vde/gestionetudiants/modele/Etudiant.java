package com.vde.gestionetudiants.modele;


import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name="new_table")
public class Etudiant {
    @Id
    @Column(name = "idetudiant")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String firstName;

    String lastName;

    String email;
}
