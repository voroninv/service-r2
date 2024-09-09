package com.base.servicer2.repositories;

import com.base.servicer2.entities.Alien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlienRepo extends JpaRepository<Alien, Integer> {

    List<Alien> findByName(@Param("name") String name);
}
