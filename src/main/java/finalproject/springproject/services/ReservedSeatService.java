package finalproject.springproject.services;

import finalproject.springproject.models.ReservedSeat;

import java.util.List;

public interface ReservedSeatService {

    ReservedSeat addReservedSeat(ReservedSeat reservedSeat);
    ReservedSeat saveReservedSeat(ReservedSeat reservedSeat);
    List<ReservedSeat> getReservedSeats();
    ReservedSeat getReservedSeat(Long id);
    void deleteReservedSeat(Long id);

}
