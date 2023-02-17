package finalproject.springproject.services;

import finalproject.springproject.models.CinemaModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CinemaService {

    CinemaModel addCinema(CinemaModel cinemaModel);
    CinemaModel saveCinema(CinemaModel cinemaModel);
    List<CinemaModel> getCinemas();
    CinemaModel getCinema(Long id);
    void deleteCinema(Long id);

    CinemaModel getCurrentUser();

    List<CinemaModel> getSortCinemas();

}
