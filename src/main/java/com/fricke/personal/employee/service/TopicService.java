package com.fricke.personal.employee.service;

import com.fricke.personal.employee.controller.Topic;
import com.fricke.personal.employee.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopicService {
    @Autowired
    private TopicRepository repository;

    public Iterator<Topic> getAllTopic() {
        return repository.findAll().iterator();
    }

    public void addAllTopic(Iterable<Topic> topicIterable) {
        repository.saveAll(topicIterable);
    }

    public void addTopic(Topic topic) {
        if (topic == null) {
            throw new IllegalArgumentException("Topic cannot be null!");
        }
        repository.save(topic);
    }

    public Topic getTopic(String id) {
        Optional<Topic> topic = repository.findById(id);
        if (topic.isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null!");
        }
        return topic.get();
    }

    public void updateTopic(Topic topic, String id) {
        Optional<Topic> updatedTopic = repository.findById(id);
        if (updatedTopic.isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null!");
        }
        repository.delete(updatedTopic.get());
        repository.save(topic);
    }

    public void deleteTopic(String id) {
        Optional<Topic> topic = repository.findById(id);
        if (topic.isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null!");
        }
        repository.delete(topic.get());
    }
}
