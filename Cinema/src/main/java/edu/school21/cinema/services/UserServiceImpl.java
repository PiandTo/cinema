package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepositoryImpl repository;

	@Override
	public void update(User user) {
		repository.update(user);
	}
	@Override
	@Transactional
	public void save(User user) {
		repository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public User findById(Long id) {
		return repository.findById(id);
	}
}
