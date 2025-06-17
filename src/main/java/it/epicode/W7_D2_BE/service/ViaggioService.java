package it.epicode.W7_D2_BE.service;


import it.epicode.W7_D2_BE.dto.ViaggioDto;
import it.epicode.W7_D2_BE.enums.StatoViaggio;
import it.epicode.W7_D2_BE.exceptions.NotFoundException;
import it.epicode.W7_D2_BE.model.Viaggio;
import it.epicode.W7_D2_BE.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;


    public Viaggio saveViaggio(ViaggioDto viaggioDto){
        Viaggio viaggio = new Viaggio();

        viaggio.setDestinazione(viaggioDto.getDestinazione());
        viaggio.setDataViaggio(viaggioDto.getDataViaggio());

        return viaggioRepository.save(viaggio);
    }

    public Viaggio getViaggio(int id) throws NotFoundException {
        return viaggioRepository.findById(id).orElseThrow(() -> new NotFoundException("Viaggio con id: " + id + " non trovato"));
    }

    public Page<Viaggio> getAllViaggi(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return viaggioRepository.findAll(pageable);
    }

    public Viaggio updateViaggio(int id, ViaggioDto viaggioDto) throws NotFoundException{
        Viaggio viaggioDaAggiornare = getViaggio(id);

        viaggioDaAggiornare.setDestinazione(viaggioDto.getDestinazione());
        viaggioDaAggiornare.setDataViaggio(viaggioDto.getDataViaggio());

        return viaggioRepository.save(viaggioDaAggiornare);
    }

    public void deleteViaggio(int id) throws NotFoundException{
        Viaggio viaggioDaCancellare = getViaggio(id);
        viaggioRepository.delete(viaggioDaCancellare);
    }

    //patch per cambiare stato
    //todo

    public Viaggio patchViaggio(int id, String nuovoStato) throws NotFoundException {
        Viaggio viaggioDaPatchare = getViaggio(id);

        StatoViaggio statoEnum;
        statoEnum = StatoViaggio.valueOf(nuovoStato.toUpperCase());
        viaggioDaPatchare.setStatoViaggio(statoEnum); //???

        return viaggioRepository.save(viaggioDaPatchare);

    }

}
