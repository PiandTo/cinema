package edu.school21.cinema.services;
import edu.school21.cinema.models.Session;

import java.util.List;

public interface SessionService {
    List<Session> findAll();
    void update(Session obj);
    Session findById(Long id);
    List<Session> findByFilmName(String filmName);
    void save(Session obj);
    void delete(Session obj);
}
