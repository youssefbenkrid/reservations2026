package be.iccbxl.pid.reservations2026.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import be.iccbxl.pid.reservations2026.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    List<User> findByLastname(String lastname);
    User findById(long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
}