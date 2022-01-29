package com.fricke.personal.employee.service;

import com.fricke.personal.employee.controller.Address;
import com.fricke.personal.employee.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public Iterator<Address> getAllAddress(){
        return repository.findAll().iterator();
    }

    public void addAddress(Address address){
        repository.save(address);
    }
}
