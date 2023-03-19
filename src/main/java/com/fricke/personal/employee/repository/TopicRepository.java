package com.fricke.personal.employee.repository;

import com.fricke.personal.employee.controller.Topic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TopicRepository extends CrudRepository<Topic, String> {
    Optional<Topic> getTopById(Long id);
    @Transactional
    @Modifying
    @Query(nativeQuery=true, value = "update beq.topic t set t.name = ?2 where t.id = ?1")
    Integer queryTopicByIdAndName(Long id, String name);
}
