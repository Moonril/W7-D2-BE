package it.epicode.W7_D2_BE.controller;


import it.epicode.W7_D2_BE.dto.DipendenteDto;
import it.epicode.W7_D2_BE.exceptions.NotFoundException;
import it.epicode.W7_D2_BE.model.Dipendente;
import it.epicode.W7_D2_BE.service.DipendenteService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/dipendenti")
public class DipendenteController {

    @Autowired
    private it.epicode.W7_D2_BE.service.DipendenteService dipendenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente (@RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult) throws NotFoundException, ValidationException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("",(e, s)->e+s));
        }
        return dipendenteService.saveDipendente(dipendenteDto);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Page<Dipendente> getAllDipendenti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue =  "id") String sortBy){
        return dipendenteService.getAllDipendenti(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Dipendente getDipendente(@PathVariable int id) throws NotFoundException {
        return dipendenteService.getDipendente(id);
    }

    @PutMapping("/{id}")
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult) throws NotFoundException, ValidationException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("",(e, s)->e+s));
        }
        return dipendenteService.updateDipendente(id, dipendenteDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable int id) throws NotFoundException{
        dipendenteService.deleteDipendente(id);
    }

    @PatchMapping("/{id}")
    public String patchDipendente(@PathVariable int id, @RequestBody MultipartFile file) throws NotFoundException, IOException {
        return dipendenteService.patchDipendente(id, file);
    }
}
