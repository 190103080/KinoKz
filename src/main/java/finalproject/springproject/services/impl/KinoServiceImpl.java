package finalproject.springproject.services.impl;

import finalproject.springproject.models.Kinoteatry;
import finalproject.springproject.repository.KinoteatryRepository;
import finalproject.springproject.services.KinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KinoServiceImpl implements KinoService {

    @Autowired
    private KinoteatryRepository kinoteatryRepository;

    @Override
    public Kinoteatry addKinoteatry(Kinoteatry kinoteatryModel) {
        return kinoteatryRepository.save(kinoteatryModel);
    }

    @Override
    public Kinoteatry saveKinoteatry(Kinoteatry kinoteatryModel) {
        Kinoteatry kinoteatry = kinoteatryRepository.findById(kinoteatryModel.getId()).orElse(null);

        if (kinoteatry != null) {
            kinoteatry.setName(kinoteatryModel.getName());
            kinoteatry.setStreet(kinoteatryModel.getStreet());

            return kinoteatryRepository.save(kinoteatry);
        }
        return null;
    }

    @Override
    public List<Kinoteatry> getKinoteatrys() {
        return kinoteatryRepository.findAll();
    }

    @Override
    public Kinoteatry getKinoteatry(Long id) {
        return kinoteatryRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteKinoteatry(Long id) {
        kinoteatryRepository.deleteById(id);
    }

    @Override
    public Kinoteatry getCurrentUser() {
        return null;
    }
}
