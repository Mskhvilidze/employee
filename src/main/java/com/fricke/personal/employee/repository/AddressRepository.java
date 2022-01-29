package com.fricke.personal.employee.repository;

import com.fricke.personal.employee.controller.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
}
