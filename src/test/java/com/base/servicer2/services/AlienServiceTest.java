package com.base.servicer2.services;

import com.base.servicer2.entities.Alien;
import com.base.servicer2.repositories.AlienRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/*
   JUnit 4.
   Need to mock all components.
*/

@RunWith(MockitoJUnitRunner.class)
public class AlienServiceTest {

    @Mock
    AlienRepo alienRepo;

    @InjectMocks
    private AlienService alienService;


    @Test
    public void findAlienByNameTest() {
        when(alienRepo.findByName(eq("Mike")))
                .thenReturn(Collections.singletonList(getAlien()));

        List<Alien> mike = alienService.findAlienByName("Mike");
        Assert.assertEquals("Mike", mike.get(0).getName());
        Assert.assertEquals("java", mike.get(0).getTech());
    }

    private Alien getAlien() {
        Alien alien = new Alien();
        alien.setId(1);
        alien.setName("Mike");
        alien.setTech("java");
        return alien;
    }
}
