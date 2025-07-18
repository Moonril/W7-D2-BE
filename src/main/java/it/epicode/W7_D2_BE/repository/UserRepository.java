package it.epicode.W7_D2_BE.repository;

import it.epicode.W7_D2_BE.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);

}
