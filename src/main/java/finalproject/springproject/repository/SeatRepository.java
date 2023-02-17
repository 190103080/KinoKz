package finalproject.springproject.repository;

import finalproject.springproject.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
