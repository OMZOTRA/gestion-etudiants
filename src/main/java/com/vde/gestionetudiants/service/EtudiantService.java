package com.vde.gestionetudiants.service;

import com.vde.gestionetudiants.modele.Etudiant;
import com.vde.gestionetudiants.repository.EtudiantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public  Iterable<Etudiant> getEtudiants() {
        return etudiantRepository.findAll();
    }

   public Etudiant getEtudiantById(Integer id) {
        return etudiantRepository.findById(id).get();
    }

  public  Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public  void deleteEtudiantById(Integer id) {
        etudiantRepository.deleteById(id);
    }

    public  Etudiant updateEtudiant(int id,Etudiant etudiant) {
        Optional<Etudiant> etu = etudiantRepository.findById(id);
        if(etu.isPresent()){
            Etudiant currentEtudiant = etu.get();
            String firstName = etudiant.getFirstName();
            if(firstName !=null){
                currentEtudiant.setFirstName(firstName);
            }
            String lastName = etudiant.getLastName();
            if(firstName !=null){
                currentEtudiant.setLastName(lastName);
            }
            String email = etudiant.getEmail();
            if(email !=null){
                currentEtudiant.setEmail(email);
            }
            etudiantRepository.save(currentEtudiant);
            return currentEtudiant;
        }else{
            return null;
        }
    }
}
