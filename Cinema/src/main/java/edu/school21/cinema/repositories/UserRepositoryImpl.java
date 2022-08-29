package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserRepositoryImpl implements CRUD<User>{
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public User findById(Long id) {
		try {
			TypedQuery<User> query = entityManager.createQuery(
					"SELECT u FROM User u WHERE u.id = :id", User.class);
			return query.setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public void save(User obj) {
		entityManager.persist(obj);
	}

	@Override
	public void delete(User obj) {
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public List<User> findByName(String name) {
		return null;
	}

	public User findByEmail(String email) {
		try {
			TypedQuery<User> query = entityManager.createQuery(
					"SELECT u FROM User u WHERE u.email = :email", User.class);
			User user = query.setParameter("email", email)
					.getSingleResult();
			return user;
		} catch (NoResultException e) {
			e.getStackTrace();
		}
		return null;
	}
}
