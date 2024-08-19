package com.base.servicer2.services;

import com.base.servicer2.entities.Alien;
import com.base.servicer2.exceptions.ResourceNotFoundException;
import com.base.servicer2.repositories.AlienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.base.servicer2.constants.Constants.NO_ALIENS_FOUND;

@Service
@Transactional
public class AlienService implements IAlienService {

    @Autowired
    AlienRepo alienRepo;

    @Override
    public List<Alien> findAllAliens() {
        return alienRepo.findAll();
    }

    @Override
    public List<Alien> findAlienByName(String name) {
        return alienRepo.findByName(name);
    }

    @Override
    public Alien findAlienById(Integer id) {
        return alienRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_ALIENS_FOUND));
    }

    @Override
    public Alien saveAlien(Alien alien) {
        return alienRepo.save(alien);
    }

    @Override
    public Alien updateAlien(Integer id, Alien alien) {
        Alien existingAlien = alienRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_ALIENS_FOUND));

        existingAlien.setName(alien.getName());
        existingAlien.setTech(alien.getTech());
        return alienRepo.save(existingAlien);
    }

    @Override
    public void deleteAlienById(Integer id) {
        alienRepo.deleteById(id);
    }
}
