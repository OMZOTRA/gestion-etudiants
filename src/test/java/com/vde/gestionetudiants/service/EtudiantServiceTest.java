package com.vde.gestionetudiants.service;

import com.vde.gestionetudiants.modele.Etudiant;
import com.vde.gestionetudiants.repository.EtudiantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    private EtudiantService etudiantService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        etudiantService = new EtudiantService(etudiantRepository);
    }
    Etudiant etudiant_1 = new Etudiant(1,"Traore","omar","omartra@hotmail.fr");

    @Test
    void shouldReturnListEtudiants() {
    }

    @Test
    void shouldReturnEtudiantById() {
    }

    @Test
    void shouldSaveEtudiant() {
    }
}
