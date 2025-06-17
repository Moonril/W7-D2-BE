package it.epicode.W7_D2_BE.service;

import it.epicode.W7_D2_BE.dto.LoginDto;
import it.epicode.W7_D2_BE.exceptions.NotFoundException;
import it.epicode.W7_D2_BE.model.User;
import it.epicode.W7_D2_BE.repository.UserRepository;
import it.epicode.W7_D2_BE.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    // verificare che l'utente esista
    // se l'utente non esiste lancia una eccezione
    // se esiste, dovrà generare il token

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) throws NotFoundException {
        User user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new NotFoundException("Utente con username: " + loginDto.getUsername() + "non trovato"));

        if(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            //if(loginDto.getPassword().equals(user.getPassword())){
            //utente autenticato, password e username giusti
            //crea il token
            return jwtTool.createToken(user);
        } else {
            throw new NotFoundException("Utente con username/password: " + loginDto.getUsername() + "non trovato");
        }

    }
}
