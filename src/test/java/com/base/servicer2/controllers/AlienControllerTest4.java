package com.base.servicer2.controllers;

import com.base.servicer2.entities.Alien;
import com.base.servicer2.services.IAlienService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlienControllerTest4 {

    @InjectMocks
    private AlienController alienController;

    @Mock
    private IAlienService alienService;

    @Test
    public void findAlienByNameTest() {
        when(alienService.findAlienByName("Mike"))
                .thenReturn(Collections.singletonList(getAlien()));

        ResponseEntity<List<Alien>> response = alienController.getAliens("Mike");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Mike", response.getBody().get(0).getName());

    }

    private Alien getAlien() {
        Alien alien = new Alien();
        alien.setId(1);
        alien.setName("Mike");
        alien.setTech("java");
        return alien;
    }

}
