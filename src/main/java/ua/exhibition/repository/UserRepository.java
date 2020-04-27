package ua.exhibition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.exhibition.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
