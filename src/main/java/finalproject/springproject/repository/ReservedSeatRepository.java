package finalproject.springproject.repository;

import finalproject.springproject.models.ReservedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long> {
}
