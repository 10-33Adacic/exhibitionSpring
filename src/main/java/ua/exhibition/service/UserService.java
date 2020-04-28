package ua.exhibition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.exhibition.domain.entity.Role;
import ua.exhibition.domain.entity.User;
import ua.exhibition.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Method save user in DB.
     *
     * @param user
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     *
     * Method only for SUPER_ADMIN!
     * Method for changing username and roles.
     *
     * @param username
     * @param form
     * @param user
     */
    public void saveUser(String username, Map<String, String> form, User user) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
    }

    /**
     * @return List of all users.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * @param username
     * @return User by username
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return userRepository.findByUsername(username);
    }

    /**
     *
     * Method update user's balance.
     *
     * @param user
     * @param money
     */
    public void updateUserBalance(User user, Long money) {
        Long userBalance = user.getAccountMoney();

        user.setAccountMoney(userBalance + money);

        userRepository.save(user);
    }
}
