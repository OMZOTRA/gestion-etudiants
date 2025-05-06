package com.vde.gestionetudiants.controller;

import com.vde.gestionetudiants.modele.Cours;
import com.vde.gestionetudiants.modele.Etudiant;
import com.vde.gestionetudiants.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("")
    public List<Etudiant> getEtudiants() {

        return etudiantService.getEtudiants();
    }

    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable("id") int id) {

        return etudiantService.getEtudiantById(id);
    }
    @PostMapping("/create")
    public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
         return etudiantService.saveEtudiant(etudiant);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEtudiant(@PathVariable("id") int id) {
        etudiantService.deleteEtudiantById(id);
        return "Etudiant deleted :" + id;
    }

    @PutMapping("/update/{id}")
    public Etudiant updateEtudiant(@PathVariable("id") int id,@RequestBody Etudiant etudiant) {
        return etudiantService.updateEtudiant(id, etudiant);
    }

    @PostMapping("/{id}/cours")
    public  Etudiant addcours(@PathVariable( name = "id") int id,@RequestBody Cours cours) {
        return  etudiantService.addCours(id,cours);
    }

}
