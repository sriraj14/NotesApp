package com.app.repositories;

import com.NotesApplication;
import com.app.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = {NotesApplication.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void createUser() {
        user = new User();
        user.setUsername("alexab");
        user.setPassword("asd234");
        user.setPasswordConfirmation("asd234");
        user.setRole("user");
        entityManager.persist(user);
    }

    @Test
    public void searchByName() {

        User found = userRepository.findByUsername("alexab");

        assertEquals("alexab", found.getUsername());

    }

    @Test
    public void deleteUser() {
        userRepository.removeByUsername("alexab");

        Assert.assertTrue(userRepository.findByUsername("alexab") == null);
    }


}
