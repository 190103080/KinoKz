package finalproject.springproject.repository;

import finalproject.springproject.models.CinemaSoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CinemaSoonRepository extends JpaRepository<CinemaSoon, Long> {
}
