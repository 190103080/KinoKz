package finalproject.springproject.services.impl;

import finalproject.springproject.models.CinemaSoon;
import finalproject.springproject.repository.CinemaSoonRepository;
import finalproject.springproject.services.CinemaSoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaSoonServiceImpl implements CinemaSoonService {

    @Autowired
    private CinemaSoonRepository cinemaSoonRepository;

    @Override
    public CinemaSoon addCinemaSoon(CinemaSoon cinemaSoon) {
        return cinemaSoonRepository.save(cinemaSoon);
    }

    @Override
    public CinemaSoon saveCinemaSoon(CinemaSoon cinemaSoon) {
        CinemaSoon checkCinemaSoon = cinemaSoonRepository.findById(cinemaSoon.getId()).orElse(null);

        if (checkCinemaSoon != null) {
            checkCinemaSoon.setName(cinemaSoon.getName());
            checkCinemaSoon.setType(cinemaSoon.getType());
            checkCinemaSoon.setDuration(cinemaSoon.getDuration());
            checkCinemaSoon.setRating(cinemaSoon.getRating());
            checkCinemaSoon.setReleaseYear(cinemaSoon.getReleaseYear());

            return cinemaSoonRepository.save(checkCinemaSoon);
        }

        return null;
    }

    @Override
    public List<CinemaSoon> getCinemasSoon() {
        return cinemaSoonRepository.findAll();
    }

    @Override
    public CinemaSoon getCinemaSoon(Long id) {
        return cinemaSoonRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteCinema(Long id) {
        cinemaSoonRepository.deleteById(id);
    }
}
