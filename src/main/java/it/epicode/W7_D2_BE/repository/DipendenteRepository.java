package it.epicode.W7_D2_BE.repository;


import it.epicode.W7_D2_BE.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {
}
