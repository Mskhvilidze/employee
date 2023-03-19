package com.fricke.personal.employee.repository;

import com.fricke.personal.employee.controller.Likes;
import com.fricke.personal.employee.controller.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

@Repository
public interface LikeRepository extends CrudRepository<Likes, String> {
    Iterator<Likes> getAllById(Long id);
}
