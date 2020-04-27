package ua.exhibition.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.exhibition.domain.entity.Exhibition;
import ua.exhibition.domain.entity.User;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

    Page<Exhibition> findAll(Pageable pageable);

    Page<Exhibition> findByShowroom(String showroom, Pageable pageable);

    Page<Exhibition> findByAuthor(User user, Pageable pageable);
}
