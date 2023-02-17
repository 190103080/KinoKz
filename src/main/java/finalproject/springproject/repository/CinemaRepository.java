package finalproject.springproject.repository;

import finalproject.springproject.models.CinemaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CinemaRepository extends JpaRepository<CinemaModel, Long> {
    List<CinemaModel> findAllByOrderByRatingDesc();
}
