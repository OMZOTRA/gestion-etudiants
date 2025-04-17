package com.vde.gestionetudiants.repository;

import com.vde.gestionetudiants.modele.Etudiant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends CrudRepository<Etudiant, Integer> {
}
