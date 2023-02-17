package finalproject.springproject.services.impl;

import finalproject.springproject.dto.UserDto;
import finalproject.springproject.models.*;
import finalproject.springproject.repository.TicketRepository;
import finalproject.springproject.repository.UserRepository;
import finalproject.springproject.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket saveTicket(Ticket ticket, Session session, Seat seat) {
        Ticket checkTicket = ticketRepository.findById(ticket.getId()).orElseThrow();
        ReservedSeat reservedSeat = new ReservedSeat();

        if(checkTicket != null) {
            checkTicket.setCost(1200);
            checkTicket.setSession(session);
            if (reservedSeat.getId() == seat.getId() && reservedSeat.getId() != seat.getId()) {
                checkTicket.setReservedSeat(reservedSeat);
            }
            return ticketRepository.save(checkTicket);
        }
        return null;
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Ticket buyTicket(Ticket ticket, User user, Seat seat) {
        Ticket checkTicket = ticketRepository.findById(ticket.getId()).orElseThrow();
        Long setReservedSeat = seat.getId();

        if(checkTicket != null) {
            checkTicket.setCost(1200);
//            checkTicket.setSession(session);
//            if (reservedSeat.getId() == seat.getId() && reservedSeat.getId() != seat.getId()) {
//                checkTicket.setReservedSeat(reservedSeat);
//            }
//            checkTicket.setUser(ticket.getUser());
            checkTicket.setReservedSeat(ticket.getReservedSeat());
            checkTicket.setUser(ticket.getUser());
            return ticketRepository.save(checkTicket);
        }
        return null;
    }
}
