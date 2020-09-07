package edu.plohoy.colosseum.service;

import edu.plohoy.colosseum.domain.Message;
import edu.plohoy.colosseum.domain.User;
import edu.plohoy.colosseum.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class MessageService {

    @Autowired
    private MessageRepo dao;

    public Page<Message> getMessageList(Pageable pageable, String filter, User currentUser) {
        if (filter != null && !filter.isEmpty()) {
            return dao.findByTag(pageable, filter, currentUser);

        } else {
            return dao.findAll(pageable, currentUser);
        }
    }

    public Page<Message> getMessageListForUser(Pageable pageable, User currentUser, User author) {
        return dao.findByUser(pageable, author);
    }

    public void save(Message message) {
        dao.save(message);
    }

    public Iterable<Message> findAll() {
        return dao.findAll();
    }
}
