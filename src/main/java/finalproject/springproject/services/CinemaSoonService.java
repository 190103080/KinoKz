package finalproject.springproject.services;

import finalproject.springproject.models.CinemaSoon;

import java.util.List;

public interface CinemaSoonService {

    CinemaSoon addCinemaSoon(CinemaSoon cinemaSoon);
    CinemaSoon saveCinemaSoon(CinemaSoon cinemaSoon);
    List<CinemaSoon> getCinemasSoon();
    CinemaSoon getCinemaSoon(Long id);
    void deleteCinema(Long id);

}
