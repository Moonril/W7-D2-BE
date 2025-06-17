package it.epicode.W7_D2_BE.controller;


import it.epicode.W7_D2_BE.dto.ViaggioDto;
import it.epicode.W7_D2_BE.exceptions.NotFoundException;
import it.epicode.W7_D2_BE.model.Viaggio;
import it.epicode.W7_D2_BE.service.ViaggioService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio saveViaggio (@RequestBody @Validated ViaggioDto viaggioDto, BindingResult bindingResult) throws NotFoundException, ValidationException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("",(e, s)->e+s));
        }
        return viaggioService.saveViaggio(viaggioDto);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Page<Viaggio> getAllViaggi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue =  "id") String sortBy){
        return viaggioService.getAllViaggi(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Viaggio getViaggio(@PathVariable int id) throws NotFoundException {
        return viaggioService.getViaggio(id);
    }

    @PutMapping("/{id}")
    public Viaggio updateViaggio(@PathVariable int id, @RequestBody @Validated ViaggioDto viaggioDto, BindingResult bindingResult) throws NotFoundException, ValidationException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("",(e, s)->e+s));
        }
        return viaggioService.updateViaggio(id, viaggioDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable int id) throws NotFoundException{
        viaggioService.deleteViaggio(id);
    }

    @PatchMapping("/{id}")
    public Viaggio patchStatoViaggio(@PathVariable int id, @RequestParam String stato) throws NotFoundException {
        return viaggioService.patchViaggio(id, stato);
    }
}
