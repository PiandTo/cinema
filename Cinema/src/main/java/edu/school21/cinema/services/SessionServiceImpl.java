package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.SessionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService
{
    @Autowired
    private SessionRepositoryImpl crudRepository;

    @Override
    public List<Session> findAll() {
        return crudRepository.findAll();
    }

    @Override
    @Transactional
    public void update(Session obj) {
        crudRepository.update(obj);
    }

    @Override
    public Session findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<Session> findByFilmName(String filmName) {
        return crudRepository.findByName(filmName);
    }

    @Override
    @Transactional
    public void save(Session obj) {
        crudRepository.save(obj);
    }

    @Override
    @Transactional
    public void delete(Session obj) {
        crudRepository.delete(obj);
    }
}
