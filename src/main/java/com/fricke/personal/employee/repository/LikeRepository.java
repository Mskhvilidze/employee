package com.fricke.personal.employee.repository;

import com.fricke.personal.employee.controller.Likes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<Likes, String> {
}
