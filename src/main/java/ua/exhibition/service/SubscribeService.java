package ua.exhibition.service;

import org.springframework.stereotype.Service;
import ua.exhibition.domain.entity.User;
import ua.exhibition.repository.UserRepository;

@Service
public class SubscribeService {

    private final UserRepository userRepository;

    public SubscribeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *
     * Method for subscribe to user.
     *
     * @param currentUser
     * @param user
     */
    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);

        userRepository.save(user);
    }

    /**
     *
     * Method for unsubscribe to user.
     *
     * @param currentUser
     * @param user
     */
    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);

        userRepository.save(user);
    }
}
