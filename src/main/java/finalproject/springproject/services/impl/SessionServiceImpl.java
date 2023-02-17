package finalproject.springproject.services.impl;

import finalproject.springproject.models.Session;
import finalproject.springproject.repository.SessionRepository;
import finalproject.springproject.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session saveSession(Session session) {
        Session checkSession = sessionRepository.findById(session.getId()).orElseThrow();

        if (checkSession != null) {
            checkSession.setData(session.getData());
            checkSession.setTime(session.getTime());
            checkSession.setHall(session.getHall());
            checkSession.setLanguage(session.getLanguage());
            checkSession.setCinemaModel(session.getCinemaModel());
            checkSession.setKinoteatry(session.getKinoteatry());

            return sessionRepository.save(checkSession);
        }

        return null;
    }

    @Override
    public List<Session> getSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Session getSession(Long id) {
        return sessionRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}
