package it.epicode.W7_D2_BE.controller;

import it.epicode.W7_D2_BE.dto.UserDto;
import it.epicode.W7_D2_BE.exceptions.NotFoundException;
import it.epicode.W7_D2_BE.model.User;
import it.epicode.W7_D2_BE.service.UserService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUser(){

        return userService.getAllUser();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) throws NotFoundException {
        return userService.getUser(id);
    }
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody @Validated UserDto userDto, BindingResult bindingResult) throws NotFoundException, ValidationException {
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().
                    stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (e,c)->e+c));
        }

        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) throws NotFoundException {
        userService.deleteUser(id);
    }
}
