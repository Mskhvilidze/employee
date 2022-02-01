package com.fricke.personal.employee.service;

import com.fricke.personal.employee.controller.Gamer;
import com.fricke.personal.employee.repository.GamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class GamerService implements UserDetailsService {
    @Autowired
    private GamerRepository repository;

    /**
     * Return all Person from the database
     * @return
     */
    public Iterator<Gamer> getAllGamer() {
        return repository.findAll().iterator();
    }

    public void addGamer(Gamer gamer) {
        repository.save(gamer);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(repository.findById("1").get().getNickname());
        return null;
    }
}
