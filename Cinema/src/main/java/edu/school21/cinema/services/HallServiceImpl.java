package edu.school21.cinema.services;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HallServiceImpl implements HallService{
    @Autowired
    private HallRepositoryImpl crudRepository;

    @Override
    public List<Hall> findAll() { return crudRepository.findAll(); }

    @Override
    @Transactional
    public void update(Hall obj) {
        crudRepository.update(obj);
    }

    @Override
    public Hall findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void save(Hall obj) {
        crudRepository.save(obj);
    }

    @Override
    public void delete(Hall obj) { crudRepository.delete(obj); }
}
