package com.fricke.personal.employee.repository;

import com.fricke.personal.employee.controller.Gamer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerRepository extends CrudRepository<Gamer, String> {
}
