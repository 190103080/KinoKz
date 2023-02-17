package finalproject.springproject.repository;

import finalproject.springproject.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
