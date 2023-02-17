package finalproject.springproject.services;

import finalproject.springproject.models.Seat;

import java.util.List;

public interface SeatService {

    List<Seat> getSeats();

    Seat getSeat(Long id);

}
