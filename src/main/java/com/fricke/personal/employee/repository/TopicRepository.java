package com.fricke.personal.employee.repository;

import com.fricke.personal.employee.controller.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, String> {
}
