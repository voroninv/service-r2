package com.base.servicer2.services;

import com.base.servicer2.entities.Alien;
import com.base.servicer2.repositories.AlienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlienService implements IAlienService {

    @Autowired
    AlienRepo alienRepo;

    @Override
    public List<Alien> findAlienByName(String name) {
        return alienRepo.findByName(name);
    }

    @Override
    public Alien saveAlien(Alien alien) {
        return alienRepo.save(alien);
    }

    @Override
    public void deleteAlienById(Integer id) {
        alienRepo.deleteById(id);
    }
}
