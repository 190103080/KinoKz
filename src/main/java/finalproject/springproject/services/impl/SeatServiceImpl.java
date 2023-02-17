package finalproject.springproject.services.impl;

import finalproject.springproject.models.Seat;
import finalproject.springproject.repository.SeatRepository;
import finalproject.springproject.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    SeatRepository seatRepository;


    @Override
    public List<Seat> getSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getSeat(Long id) {
        return seatRepository.findById(id).orElseThrow();
    }
}
