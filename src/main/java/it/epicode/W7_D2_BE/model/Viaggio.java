package it.epicode.W7_D2_BE.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.epicode.W7_D2_BE.enums.StatoViaggio;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Viaggio {
    @Id
    @GeneratedValue
    private int id;
    private String destinazione;
    private LocalDate dataViaggio;
    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio = StatoViaggio.In_PROGRAMMA;

    @JsonIgnore
    @OneToMany(mappedBy = "viaggio")
    private List<Prenotazione> prenotazioni;

}
