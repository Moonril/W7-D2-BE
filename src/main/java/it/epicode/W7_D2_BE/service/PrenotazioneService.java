package it.epicode.W7_D2_BE.service;


import it.epicode.W7_D2_BE.dto.PrenotazioneDto;
import it.epicode.W7_D2_BE.exceptions.NotFoundException;
import it.epicode.W7_D2_BE.exceptions.PrenotazioneGiaEsistenteException;
import it.epicode.W7_D2_BE.model.Dipendente;
import it.epicode.W7_D2_BE.model.Prenotazione;
import it.epicode.W7_D2_BE.model.Viaggio;
import it.epicode.W7_D2_BE.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private DipendenteService dipendenteService;

    public Prenotazione savePrenotazione(PrenotazioneDto prenotazioneDto) throws NotFoundException {
        Viaggio viaggio = viaggioService.getViaggio(prenotazioneDto.getViaggioId());
        Dipendente dipendente = dipendenteService.getDipendente(prenotazioneDto.getDipendenteId());

        boolean giaPrenotato = prenotazioneRepository
                .existsByDipendenteIdAndViaggio_DataViaggio(dipendente.getId(), viaggio.getDataViaggio());

        if (giaPrenotato) {
            throw new PrenotazioneGiaEsistenteException("Il dipendente ha già una prenotazione per quel giorno.");
        }

        Prenotazione prenotazione = new Prenotazione();

        prenotazione.setPreferenze(prenotazioneDto.getPreferenze());
        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);

        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione getPrenotazione(int id) throws NotFoundException {
        return prenotazioneRepository.findById(id).orElseThrow(() -> new NotFoundException("Prenotazione con id: " + id + " non trovata"));
    }

    public Page<Prenotazione> getAllPrenotazioni(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione updatePrenotazione(int id, PrenotazioneDto prenotazioneDto) throws NotFoundException{
        Prenotazione prenotazioneDaAggiornare = getPrenotazione(id);

        prenotazioneDaAggiornare.setPreferenze(prenotazioneDto.getPreferenze());


        if(prenotazioneDaAggiornare.getViaggio().getId()!=prenotazioneDto.getViaggioId()){
            Viaggio viaggio = viaggioService.getViaggio(prenotazioneDto.getViaggioId());
            prenotazioneDaAggiornare.setViaggio(viaggio);
        }
        return prenotazioneRepository.save(prenotazioneDaAggiornare);
    }

    public void deletePrenotazione(int id) throws NotFoundException{
        Prenotazione prenotazioneToDelete = getPrenotazione(id);
        prenotazioneRepository.delete(prenotazioneToDelete);
    }

    //patch cambia utente
    //todo


}
