package com.vde.gestionetudiants.controller;

import com.vde.gestionetudiants.modele.Etudiant;
import com.vde.gestionetudiants.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("/etudiants")
    public Iterable<Etudiant> getEtudiants() {
        return etudiantService.getEtudiants();
    }
    @GetMapping("/etudiant/{id}")
    public Etudiant getEtudiantById(@PathVariable("id") int id) {
        return etudiantService.getEtudiantById(id);
    }

    @PostMapping("/etudiant/add")
    public String addEtudiant(@RequestBody Etudiant etudiant) {
            etudiantService.saveEtudiant(etudiant);
            return "Etudiant added :" + etudiant;
    }

    @DeleteMapping("/etudiant/delete/{id}")
    public String deleteEtudiant(@PathVariable("id") int id) {
        etudiantService.deleteEtudiantById(id);
        return "Etudiant deleted :" + id;
    }

    @PutMapping("/etudiant/update/{id}")
    public Etudiant updateEtudiant(@PathVariable("id") int id,@RequestBody Etudiant etudiant) {
        return etudiantService.updateEtudiant(id, etudiant);
    }
}
