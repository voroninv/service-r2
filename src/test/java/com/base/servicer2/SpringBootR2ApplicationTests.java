package com.base.servicer2;

import com.base.servicer2.controllers.AlienController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootR2ApplicationTests {

    @Autowired
    private AlienController alienController;

    @Test
    void contextLoads() {
        Assertions.assertThat(alienController).isNotNull();
    }
}
