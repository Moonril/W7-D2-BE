package it.epicode.W7_D2_BE.repository;


import it.epicode.W7_D2_BE.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

    boolean existsByDipendenteIdAndViaggio_DataViaggio(Integer dipendenteId, LocalDate dataViaggio);
}
