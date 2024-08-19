package com.base.servicer2.services;

import com.base.servicer2.entities.Alien;

import java.util.List;

public interface IAlienService {

    List<Alien> findAllAliens();

    List<Alien> findAlienByName(String name);

    Alien findAlienById(Integer id);

    Alien saveAlien(Alien alien);

    Alien updateAlien(Integer id, Alien alien);

    void deleteAlienById(Integer id);
}