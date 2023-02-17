package finalproject.springproject.repository;

import finalproject.springproject.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface HallRepository extends JpaRepository<Hall, Long> {
}
