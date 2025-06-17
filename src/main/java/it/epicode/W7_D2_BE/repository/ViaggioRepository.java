package it.epicode.W7_D2_BE.repository;


import it.epicode.W7_D2_BE.model.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio, Integer> {
}
