package com.fricke.personal.employee.repository;

import com.fricke.personal.employee.controller.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends CrudRepository<Topic, String> {
    Optional<Topic> getTopById(Long id);
}
