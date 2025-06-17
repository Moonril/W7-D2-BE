package it.epicode.W7_D2_BE.service;

import com.cloudinary.Cloudinary;
import it.epicode.W7_D2_BE.dto.DipendenteDto;
import it.epicode.W7_D2_BE.exceptions.NotFoundException;
import it.epicode.W7_D2_BE.model.Dipendente;
import it.epicode.W7_D2_BE.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private Cloudinary cloudinary;

    public Dipendente saveDipendente(DipendenteDto dipendenteDto) throws NotFoundException {
        Dipendente dipendente = new Dipendente();

        dipendente.setNome(dipendenteDto.getNome());
        dipendente.setCognome(dipendenteDto.getCognome());
        dipendente.setUsername(dipendenteDto.getUsername());
        dipendente.setEmail(dipendenteDto.getEmail());

        return dipendenteRepository.save(dipendente);
    }

    public Dipendente getDipendente (int id) throws NotFoundException {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Dipendente con id: " + id + " non trovato"));
    }

    public Page<Dipendente> getAllDipendenti(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente updateDipendente(int id, DipendenteDto dipendenteDto) throws NotFoundException {
        Dipendente dipendenteDaAggiornare = getDipendente(id);

        dipendenteDaAggiornare.setNome(dipendenteDto.getNome());
        dipendenteDaAggiornare.setCognome(dipendenteDto.getCognome());
        dipendenteDaAggiornare.setUsername(dipendenteDto.getUsername());
        dipendenteDaAggiornare.setEmail(dipendenteDto.getEmail());

        return  dipendenteRepository.save(dipendenteDaAggiornare);
    }

    public void deleteDipendente(int id) throws NotFoundException{
        Dipendente dipendenteDaCancellare = getDipendente(id);

        dipendenteRepository.delete(dipendenteDaCancellare);
    }

    public String patchDipendente(int id, MultipartFile file) throws NotFoundException, IOException {
        Dipendente dipendeteDaPatchare = getDipendente(id);

        String url = (String)cloudinary.uploader().upload(file.getBytes(), Collections.emptyMap()).get("url");

        dipendeteDaPatchare.setUrlAvatar(url);

        dipendenteRepository.save(dipendeteDaPatchare);

        return url;
    }

}
