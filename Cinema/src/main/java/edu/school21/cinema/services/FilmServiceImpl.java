package edu.school21.cinema.services;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.repositories.FilmRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepositoryImpl crudRepository;

    @Override
    public List<Film> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public void update(Film obj) {
        crudRepository.update(obj);
    }

    @Override
    public Film findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void save(Film obj) {
        crudRepository.save(obj);
    }

    @Override
    public void delete(Film obj) { crudRepository.delete(obj); }

}
