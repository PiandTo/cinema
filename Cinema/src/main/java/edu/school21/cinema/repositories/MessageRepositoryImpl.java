package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements CRUD<Message> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void update(Message obj) {
    }

    @Override
    public Message findById(Long id) {
        return null;
    }

    @Override
    public void save(Message obj) {
        entityManager.persist(obj);
    }

    @Override
    public void delete(Message obj) {

    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public List<Message> findByName(String name) {
        return null;
    }

    public List<Message> findByFilmId (Long id) {
    List<Message> messageList = entityManager.createQuery("SELECT l FROM Message l WHERE l.film.id = ?1 ORDER BY id DESC")
            .setParameter(1, id)
            .getResultList();
    List<Message> newMessageList = new ArrayList<>();
    int len = messageList.size() > 19 ? 20 : messageList.size();
        for(int i = 0; i < len; i++)
            newMessageList.add(messageList.get(i));
        Collections.reverse(newMessageList);
        return newMessageList;
    }
}
