package com.base.servicer2.controllers;

import com.base.servicer2.entities.Alien;
import com.base.servicer2.exceptions.BadRequestException;
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

import static com.base.servicer2.constants.Constants.INCORRECT_REQUEST_PARAMETERS;
import static com.base.servicer2.constants.Constants.NO_ALIENS_FOUND;

@RestController
@RequestMapping("/r2/api")
public class AlienController {

    private static final Logger logger = LogManager.getLogger(AlienController.class);

    @Autowired
    IAlienService alienService;

    @GetMapping("/alien")
    public ResponseEntity<Alien> findAlienByName(@RequestParam String search) {
        logger.info("r2: looking for alien.");

        if (StringUtils.isBlank(search)) {
            throw new BadRequestException(INCORRECT_REQUEST_PARAMETERS);
        }

        List<Alien> aliens = alienService.findAlienByName(search);

        if (aliens.isEmpty()) {
            throw new ResourceNotFoundException(NO_ALIENS_FOUND);
        }
        logger.info("r2: alien found.");

        return ResponseEntity.ok(aliens.get(0));
    }

    @PostMapping
    public ResponseEntity<Alien> saveAlien(@RequestBody Alien alien) {
        Alien alienResponse = alienService.saveAlien(alien);

        return ResponseEntity.ok(alienResponse);
    }

    @DeleteMapping
    public ResponseEntity<Integer> deleteAlienById(@RequestParam Integer id) {
        try {
            alienService.deleteAlienById(id);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
