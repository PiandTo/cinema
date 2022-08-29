package edu.school21.cinema.services;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.MessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{
    @Autowired
    MessageRepositoryImpl messageRepository;

    @Override
    public void save(Message msg) {
        messageRepository.save(msg);
    }

    @Override
    public List<Message> findByFilmId(Long id) {
        return messageRepository.findByFilmId(id);
    }
}
