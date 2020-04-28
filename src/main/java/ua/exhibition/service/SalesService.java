package ua.exhibition.service;

import org.springframework.stereotype.Service;
import ua.exhibition.exception.BuyException;
import ua.exhibition.domain.entity.Exhibition;
import ua.exhibition.domain.entity.User;
import ua.exhibition.repository.ExhibitionRepository;
import ua.exhibition.repository.UserRepository;

import java.util.List;

@Service
public class SalesService {

    private final UserRepository userRepository;

    private final ExhibitionRepository exhibitionRepository;

    public SalesService(UserRepository userRepository, ExhibitionRepository exhibitionRepository) {
        this.userRepository = userRepository;
        this.exhibitionRepository = exhibitionRepository;
    }

    /**
     * Add ticket to user's field boughtTickets,
     * if he has enough money,
     * and he hasn't this ticket.
     *
     * @param user
     * @param ticketId
     * @throws BuyException
     */
    public void addTicket(User user, Long ticketId) throws BuyException {
        Exhibition currentTicket = exhibitionRepository.findById(ticketId).get();

        if (!user.getBoughtTickets().contains(currentTicket)
                && user.getAccountMoney() >= currentTicket.getPrice()) {

            user.getBoughtTickets().add(currentTicket);

            user.setAccountMoney(user.getAccountMoney() - currentTicket.getPrice());

            userRepository.save(user);
        } else {
            throw new BuyException();
        }
    }

    /**
     *
     * @param user
     * @return List of user's tickets
     */
    public List<Exhibition> findUserTickets(User user) {
        return user.getBoughtTickets();
    }
}
