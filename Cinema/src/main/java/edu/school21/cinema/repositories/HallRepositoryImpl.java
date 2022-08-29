package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HallRepositoryImpl implements CRUD<Hall>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void update(Hall hall) {
        entityManager.merge(hall);
    }

    @Override
    public Hall findById(Long id) {
       return (Hall)entityManager.createQuery("from Hall hall where hall.id = " + id).getSingleResult();
    }

    @Override
    public void save(Hall obj) {
        entityManager.persist(obj);
    }

    @Override
    public void delete(Hall obj) {
        entityManager.remove(entityManager.contains(obj) ? obj : entityManager.merge(obj));
    }

    @Override
    public List<Hall> findAll() {
        return entityManager.createQuery("from Hall", Hall.class).getResultList();
    }

    @Override
    public List<Hall> findByName(String name) {
        return null;
    }
}
