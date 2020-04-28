package ua.exhibition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.exhibition.domain.entity.User;
import ua.exhibition.repository.UserRepository;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Method change username if it differ from old username.
     * And change password if it isn't null.
     *
     * @param user
     * @param username
     * @param password
     */
    public void updateProfile(User user, String username, String password) {
        String oldUsername = user.getUsername();

        boolean isUsernameChanged = (username != null && !username.equals(oldUsername))
                || (oldUsername != null && !oldUsername.equals(username));

        if (isUsernameChanged) {
            user.setUsername(username);
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        userRepository.save(user);
    }
}
