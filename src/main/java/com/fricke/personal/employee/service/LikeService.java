package com.fricke.personal.employee.service;

import com.fricke.personal.employee.controller.Likes;
import com.fricke.personal.employee.controller.Topic;
import com.fricke.personal.employee.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class LikeService {

    @Autowired
    LikeRepository repository;

    public Iterator<Likes> getAllLikes() {
        return repository.findAll().iterator();
    }

    public void likeSave(Likes like) {
        if (like == null) {
            throw new IllegalArgumentException("Likes cannot be null!");
        }
        repository.save(like);
    }

    public void disLike(Likes like) {
        if (like == null) {
            throw new IllegalArgumentException("Likes cannot be null!");
        }
        repository.delete(like);
    }

    public boolean isContains(Likes likes) {
        if (likes == null) {
            throw new IllegalArgumentException("Likes cannot be null!");
        }
        Iterator<Likes> likesIterator = repository.findAll().iterator();
        Spliterator<Likes> spliterators = Spliterators.spliteratorUnknownSize(likesIterator, Spliterator.ORDERED);
        Stream<Likes> likesStream = StreamSupport.stream(spliterators, false);
        Optional<Likes> temp =
                likesStream.filter(l -> l.getTopicId().equals(likes.getTopicId()) && l.getUserID().equals(likes.getUserID())).findFirst();
        if (temp.isPresent()) {
            return true;
        }
        return false;
    }

    public Long getCountLikes(String topicID) {
        Iterator<Likes> likesIterator = repository.findAll().iterator();
        Spliterator<Likes> spliterators = Spliterators.spliteratorUnknownSize(likesIterator, Spliterator.ORDERED);
        Stream<Likes> likesStream = StreamSupport.stream(spliterators, false);

        return likesStream.filter(f -> f.getTopicId().equals(topicID)).count();
    }
}
