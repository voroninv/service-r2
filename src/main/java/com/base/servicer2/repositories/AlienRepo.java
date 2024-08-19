package com.base.servicer2.repositories;

import com.base.servicer2.entities.Alien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlienRepo extends JpaRepository<Alien, Integer> {

//    List<Alien> findAll();

    List<Alien> findByName(@Param("name") String name);

//    Optional<Alien> findById(Integer integer);

//    <S extends Alien> S save(S entity);

//    void deleteById(Integer id);
}
