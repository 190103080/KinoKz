package finalproject.springproject.services;

import finalproject.springproject.models.CinemaModel;
import finalproject.springproject.models.Session;

import java.util.List;

public interface SessionService {

    Session addSession(Session session);
    Session saveSession(Session session);
    List<Session> getSessions();
    Session getSession(Long id);
    void deleteSession(Long id);

}
