package ua.exhibition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.exhibition.domain.entity.Exhibition;
import ua.exhibition.domain.entity.User;
import ua.exhibition.repository.ExhibitionRepository;

@Service
public class ExhibitionService {

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    /**
     * Save entity exhibition un repository.
     *
     * @param exhibition
     */
    public void save(Exhibition exhibition) {
        exhibitionRepository.save(exhibition);
    }

    /**
     * @param pageable, default 10
     * @return page of exhibitions
     */
    public Page<Exhibition> findAll(Pageable pageable) {
        return exhibitionRepository.findAll(pageable);
    }

    /**
     * @param showroom
     * @param pageable
     * @return page of exhibitions, sorting by showroom
     */
    public Page<Exhibition> findByShowroom(String showroom, Pageable pageable) {
        return exhibitionRepository.findByShowroom(showroom, pageable);
    }

    /**
     * @param user
     * @param pageable
     * @return page of exhibitions, sorting by author
     */
    public Page<Exhibition> findByAuthor(User user, Pageable pageable) {
        return exhibitionRepository.findByAuthor(user, pageable);
    }

    /**
     * Delete exhibition by id param.
     *
     * @param id
     */
    public void deleteById(Long id) {
        exhibitionRepository.deleteById(id);
    }

    /**
     * @param id
     * @return entity of exhibition
     */
    public Exhibition findById(Long id) {
        return exhibitionRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
