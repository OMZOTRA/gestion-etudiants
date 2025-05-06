package com.vde.gestionetudiants.controller;


import com.vde.gestionetudiants.modele.Cours;
import com.vde.gestionetudiants.repository.CourRepository;
import com.vde.gestionetudiants.service.CourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cours")
public class CourController {

    @Autowired
    private CourService courService;

    @GetMapping("")
    public List<Cours> getAllCours(){
        return courService.getAllCour();
    }

    @GetMapping("/{id}")
    public Optional<Cours> getCourById(@PathVariable("id") int id) {
        return courService.getCour(id);
    }

    @PostMapping("/create")
    public Cours  createCour(@RequestBody Cours cour) {
       return  courService.saveCour(cour);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourById(@PathVariable("id") int id) {
        courService.deleteCour(id);
        return "Cour deleted :" + id;
    }
}
