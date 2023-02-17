package finalproject.springproject.services;

import finalproject.springproject.dto.UserDto;
import finalproject.springproject.models.*;

import java.util.List;

public interface TicketService {

    Ticket addTicket(Ticket ticket);
    Ticket saveTicket(Ticket ticket, Session session, Seat seat);
    List<Ticket> getTickets();
    Ticket getTicket(Long id);
    void deleteTicket(Long id);

    Ticket buyTicket(Ticket ticket, User user, Seat seat);

}
