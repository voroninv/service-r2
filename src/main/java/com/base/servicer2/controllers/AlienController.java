package com.base.servicer2.controllers;

import com.base.servicer2.entities.Alien;
import com.base.servicer2.exceptions.ResourceNotFoundException;
import com.base.servicer2.services.IAlienService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.base.servicer2.constants.Constants.NO_ALIENS_FOUND;

@RestController
@RequestMapping("/api")
public class AlienController {

    private static final Logger logger = LogManager.getLogger(AlienController.class);

    @Autowired
    IAlienService alienService;

    @GetMapping("/aliens")
    public ResponseEntity<List<Alien>> getAliens(@RequestParam(required = false) String name) {
        logger.info("r2: get aliens request received.");

        List<Alien> aliens;
        if (StringUtils.isBlank(name)) {
            aliens = alienService.findAllAliens();
        } else {
            aliens = alienService.findAlienByName(name);
        }

        if (aliens.isEmpty()) {
            throw new ResourceNotFoundException(NO_ALIENS_FOUND);
        }
        logger.info("r2: get aliens request processed.");

        return ResponseEntity.ok(aliens);
    }

    @GetMapping("/aliens/{id}")
    public ResponseEntity<Alien> getAlienById(@PathVariable Integer id) {
        logger.info("r2: get alien request received.");

        Alien alien = alienService.findAlienById(id);

        logger.info("r2: get alien request processed.");

        return ResponseEntity.ok(alien);
    }

    @PostMapping("/aliens")
    public ResponseEntity<Alien> saveAlien(@RequestBody Alien alien) {
        logger.info("r2: save alien request received.");

        Alien alienResponse = alienService.saveAlien(alien);

        logger.info("r2: save alien request processed.");

        return ResponseEntity.status(HttpStatus.CREATED).body(alienResponse);
    }

    @PutMapping("/aliens/{id}")
    public ResponseEntity<Alien> updateAlien(@PathVariable Integer id, @RequestBody Alien alien) {
        logger.info("r2: update alien request received.");

        Alien alienResponse = alienService.updateAlien(id, alien);

        logger.info("r2: update alien request processed.");

        return ResponseEntity.ok(alienResponse);
    }

    @DeleteMapping("/aliens/{id}")
    public ResponseEntity<Integer> deleteAlienById(@PathVariable Integer id) {
        logger.info("r2: delete alien request received.");

        alienService.deleteAlienById(id);

        logger.info("r2: delete alien request processed.");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(id);
    }
}
