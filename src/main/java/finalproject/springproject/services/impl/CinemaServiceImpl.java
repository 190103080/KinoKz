package finalproject.springproject.services.impl;

import finalproject.springproject.models.CinemaModel;
import finalproject.springproject.repository.CinemaRepository;
import finalproject.springproject.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public CinemaModel addCinema(CinemaModel cinemaModel) {
        return cinemaRepository.save(cinemaModel);
    }

    @Override
    public CinemaModel saveCinema(CinemaModel cinemaModel) {
        CinemaModel cinemaCheck = cinemaRepository.findById(cinemaModel.getId()).orElse(null);

        if (cinemaCheck != null) {
            cinemaCheck.setName(cinemaModel.getName());
            cinemaCheck.setType(cinemaModel.getType());
            cinemaCheck.setDuration(cinemaModel.getDuration());
            cinemaCheck.setRating(cinemaModel.getRating());
            cinemaCheck.setReleaseYear(cinemaModel.getReleaseYear());
            cinemaCheck.setImage(cinemaModel.getImage());

            return cinemaRepository.save(cinemaCheck);
        }

        return null;
    }

    @Override
    public List<CinemaModel> getCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public CinemaModel getCinema(Long id) {
        return cinemaRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteCinema(Long id) {
        cinemaRepository.deleteById(id);
    }

    @Override
    public CinemaModel getCurrentUser() {
        return null;
    }

    @Override
    public List<CinemaModel> getSortCinemas() {
        List<CinemaModel> cinemaModel = cinemaRepository.findAllByOrderByRatingDesc();
        return cinemaModel;
    }
}
