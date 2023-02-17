package finalproject.springproject.services;

import finalproject.springproject.models.Hall;

import java.util.List;

public interface HallService {

    Hall getHall(Long id);
    List<Hall> getHalls();

}
