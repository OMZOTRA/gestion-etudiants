package com.vde.gestionetudiants.service;

import com.vde.gestionetudiants.modele.Cours;
import com.vde.gestionetudiants.repository.CourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourService {

    @Autowired
    private CourRepository courRepository;


    public List<Cours> getAllCour(){
        return courRepository.findAll();
    }

    public Optional<Cours> getCour(Integer id) {
        return courRepository.findById(id);
    }

    public Cours saveCour(Cours cour) {
       return courRepository.save(cour);
    }

    public  void deleteCour(Integer id) {
        courRepository.deleteById(id);
    }

}
