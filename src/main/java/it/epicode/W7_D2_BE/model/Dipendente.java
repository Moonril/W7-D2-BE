package it.epicode.W7_D2_BE.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Dipendente {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String urlAvatar;

    @JsonIgnore
    @OneToMany(mappedBy = "dipendente")
    private List<Prenotazione> prenotazioni;

}
