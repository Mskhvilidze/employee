package com.fricke.personal.employee.service;

import com.fricke.personal.employee.controller.Gamer;
import com.fricke.personal.employee.repository.GamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class GamerService {
    @Autowired
    private GamerRepository repository;

    /**
     * Return all Person from the database
     *
     * @return
     */
    public Iterator<Gamer> getAllGamer() {
        return repository.findAll().iterator();
    }

    public void addGamer(Gamer gamer) {
        repository.save(gamer);
    }

    public Gamer getGamer(String id) {
        Optional<Gamer> gamer = repository.findByNickname(id);
        if (gamer.isEmpty()) {
            throw new IllegalArgumentException("Gamer cannot be null!");
        }
        return gamer.get();
    }

    public boolean isExistGamer(String id) {
        Optional<Gamer> gamer = repository.findByNickname(id);
        return gamer.isPresent();
    }

    public boolean isCompareNicknameWithSession(String nickname, Object session) {
        Optional<Gamer> gamer = repository.findByNickname(nickname);
        if (gamer.isEmpty()) {
            throw new IllegalArgumentException("Gamer cannot be null!");
        }

        if (gamer.get().getNickname().equals(session.toString())) {
            return true;
        }
        return false;
    }
}
