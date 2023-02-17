package finalproject.springproject.services;

import finalproject.springproject.models.Kinoteatry;

import javax.swing.*;
import java.util.List;

public interface KinoService {

    Kinoteatry addKinoteatry(Kinoteatry kinoteatryModel);
    Kinoteatry saveKinoteatry(Kinoteatry kinoteatryModel);
    List<Kinoteatry> getKinoteatrys();
    Kinoteatry getKinoteatry(Long id);
    void deleteKinoteatry(Long id);

    Kinoteatry getCurrentUser();
}
