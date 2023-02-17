package finalproject.springproject.services.impl;

import finalproject.springproject.models.Hall;
import finalproject.springproject.repository.HallRepository;
import finalproject.springproject.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    HallRepository hallRepository;

    @Override
    public Hall getHall(Long id) {
        return hallRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Hall> getHalls() {
        return hallRepository.findAll();
    }
}
