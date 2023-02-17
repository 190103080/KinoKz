package finalproject.springproject.services.impl;

import finalproject.springproject.models.ReservedSeat;
import finalproject.springproject.repository.ReservedSeatRepository;
import finalproject.springproject.services.ReservedSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservedSeatRepositoryImpl implements ReservedSeatService {

    @Autowired
    ReservedSeatRepository reservedSeatRepository;


    @Override
    public ReservedSeat addReservedSeat(ReservedSeat reservedSeat) {
        return reservedSeatRepository.save(reservedSeat);
    }

    @Override
    public ReservedSeat saveReservedSeat(ReservedSeat reservedSeat) {
        ReservedSeat checkReservedSeat = reservedSeatRepository.findById(reservedSeat.getId()).orElseThrow();

        if (checkReservedSeat != null) {
            checkReservedSeat.setTicket(reservedSeat.getTicket());

            return reservedSeatRepository.save(checkReservedSeat);
        }

        return null;
    }

    @Override
    public List<ReservedSeat> getReservedSeats() {
        return reservedSeatRepository.findAll();
    }

    @Override
    public ReservedSeat getReservedSeat(Long id) {
        return reservedSeatRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteReservedSeat(Long id) {
        reservedSeatRepository.deleteById(id);
    }
}
