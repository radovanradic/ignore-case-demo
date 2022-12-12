package com.example;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import java.util.Optional;

@MicronautTest
class IgnoreCaseDemoTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    UserRepository userRepository;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testIgnoreCase() {
        User user1 = new User();
        user1.setName("Radovan");
        user1.setEmail("RADOVAN.RADIC@oracle.COM");
        userRepository.save(user1);

        Optional<User> optUser = userRepository.findByEmailIgnoreCase("radovan.radic@oracle.com");
        Assertions.assertTrue(optUser.isPresent());
        Assertions.assertEquals(user1.getId(), optUser.get().getId());
        Assertions.assertEquals(user1.getEmail(), optUser.get().getEmail());
    }
}
