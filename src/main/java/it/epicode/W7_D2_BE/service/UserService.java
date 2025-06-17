package it.epicode.W7_D2_BE.service;

import it.epicode.W7_D2_BE.dto.UserDto;
import it.epicode.W7_D2_BE.enums.Role;
import it.epicode.W7_D2_BE.exceptions.NotFoundException;
import it.epicode.W7_D2_BE.model.User;
import it.epicode.W7_D2_BE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //password encoding
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserDto userDto){
        User user = new User();
        user.setNome(userDto.getNome());
        user.setCognome(userDto.getCognome());
        user.setUsername(userDto.getUsername());
        //user.setPassword(userDto.getPassword()); // prendiamo la password da dto e la assegnamo in chiaro
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // così stessa storia ma non chiaro
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public List<User> getAllUser(){

        return userRepository.findAll();
    }

    public User getUser(int id) throws NotFoundException {
        return userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User con id " + id + " non trovato"));
    }

    public User updateUser(int id, UserDto userDto) throws NotFoundException {
        User userDaAggiornare = getUser(id);

        userDaAggiornare.setNome(userDto.getNome());
        userDaAggiornare.setCognome(userDto.getCognome());
        userDaAggiornare.setUsername(userDto.getUsername());
        //userDaAggiornare.setPassword(userDto.getPassword()); // in chiaro
        //controllare se la password è uguale
        if(!passwordEncoder.matches(userDto.getPassword(), userDaAggiornare.getPassword())){
            userDaAggiornare.setPassword(passwordEncoder.encode(userDto.getPassword()));

        }

        return userRepository.save(userDaAggiornare);
    }

    public void deleteUser(int id) throws NotFoundException {
        User userDaCancellare = getUser(id);

        userRepository.delete(userDaCancellare);
    }
}
