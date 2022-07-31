package com.fricke.personal.employee.service;

import com.fricke.personal.employee.controller.Topic;
import com.fricke.personal.employee.repository.TopicRepository;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

    public Topic getTopic(Long id) {
        Optional<Topic> topic = repository.getTopById(id);
        if (topic.isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null!");
        }
        return topic.get();
    }

    public void updateTopic(Topic topic, Long id) {
        Optional<Topic> updatedTopic = repository.getTopById(id);
        if (updatedTopic.isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null!");
        }
        repository.save(topic);
    }

    public void deleteTopic(Long id) {
        Optional<Topic> topic = repository.getTopById(id);
        if (topic.isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null!");
        }
        repository.delete(topic.get());
    }

    public String getNumberOfTopics(String nickname) {
        Iterator<Topic> iterator = repository.findAll().iterator();
        Spliterator<Topic> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.SORTED);
        Stream<Topic> stream = StreamSupport.stream(spliterator, false);
        int count = 0;
        count = (int) stream.filter(n -> n.getGamer().getNickname().equals(nickname)).count();

        return String.valueOf(count);
    }

    public String getRanking() {
        Iterator<Topic> iterator = repository.findAll().iterator();
        Spliterator<Topic> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.SORTED);
        Stream<Topic> stream = StreamSupport.stream(spliterator, false);

        Map<String, Long> map = new HashMap<>();
        List<Topic> list = stream.distinct().collect(Collectors.toList());
        
        return "";
    }
}
