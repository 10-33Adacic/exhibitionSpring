package ua.exhibition.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.exhibition.domain.entity.User;
import ua.exhibition.repository.UserRepository;

import java.util.Collections;

import static ua.exhibition.domain.entity.Role.*;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * Add user in DB, if user with doesn't exist with this username.
     *
     * @param user
     * @return
     */
    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setAccountMoney(0L);
        user.setRoles(Collections.singleton(USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return true;
    }
}