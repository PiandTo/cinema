package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Logs;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LogRepositoryImpl implements CRUD<Logs>{
	@PersistenceContext
	EntityManager em;

	@Override
	public void update(Logs obj) {

	}

	@Override
	public Logs findById(Long id) {
		return null;
	}

	@Override
	public void save(Logs obj) {
		em.persist(obj);
	}

	@Override
	public void delete(Logs obj) {

	}

	@Override
	public List<Logs> findAll() {
		return null;
	}

	@Override
	public List<Logs> findByName(String name) {
		return em.createQuery("SELECT l FROM Logs l WHERE l.email LIKE ?1")
				.setParameter(1, name)
				.getResultList();
	}
}
