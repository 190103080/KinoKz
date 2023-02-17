package finalproject.springproject.repository;

import finalproject.springproject.models.Kinoteatry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface KinoteatryRepository extends JpaRepository<Kinoteatry, Long> {
}
