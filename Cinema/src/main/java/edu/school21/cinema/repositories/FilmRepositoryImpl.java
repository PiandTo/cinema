package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FilmRepositoryImpl implements CRUD<Film> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void update(Film film) {
        entityManager.merge(film);
    }

    @Override
    public Film findById(Long id) {
        return (Film) entityManager.createQuery("from Film film where film.id =" +id).getSingleResult();
    }

    @Override
    public void save(Film obj) {
        entityManager.persist(obj);
    }

    @Override
    public void delete(Film obj) {
        entityManager.remove(entityManager.contains(obj) ? obj : entityManager.merge(obj));
    }

    @Override
    public List<Film> findAll() {
        return entityManager.createQuery("from Film", Film.class).getResultList();
    }

    @Override
    public List<Film> findByName(String name) {
        return null;
    }
}
