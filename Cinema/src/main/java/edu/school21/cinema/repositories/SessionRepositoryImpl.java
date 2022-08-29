package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SessionRepositoryImpl implements CRUD<Session> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void update(Session session) {
        entityManager.merge(session);
    }

    @Override
    public Session findById(Long id) {
        Session session = entityManager.find(Session.class, id);
        if (session == null) {
            throw new EntityNotFoundException("Can't find Session for ID "
                    + id);
        }
        return session;
    }

    @Override
    public void save(Session session) {
        entityManager.merge(session);
    }

    @Override
    public void delete(Session obj) {
        entityManager.remove(entityManager.contains(obj) ? obj : entityManager.merge(obj));
    }

    @Override
    public List<Session> findAll() {
        return entityManager.createQuery("from Session ", Session.class).getResultList();
    }

    @Override
    public List<Session> findByName(String title) {
        TypedQuery<Session> query = entityManager.createQuery(
                "SELECT s FROM Session s WHERE LOWER(s.film.title) LIKE LOWER(CONCAT('%', :title,'%'))",
                Session.class);
        return query.setParameter("title", title).getResultList();
    }
}
