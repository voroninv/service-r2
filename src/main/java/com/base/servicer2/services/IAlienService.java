package com.base.servicer2.services;

import com.base.servicer2.entities.Alien;

import java.util.List;

public interface IAlienService {

    List<Alien> findAlienByName(String name);

    Alien saveAlien(Alien alien);

    void deleteAlienById(Integer id);
}