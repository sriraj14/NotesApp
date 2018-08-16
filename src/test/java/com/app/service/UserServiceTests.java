package com.app.service;

import com.app.config.SpringSecurityConfig;
import com.app.exceptions.UsernameExistsException;
import com.app.model.User;
import com.app.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

@RunWith(SpringRunner.class)
@Import(SpringSecurityConfig.class)
@SpringBootTest
@Transactional
public class UserServiceTests {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private User user;

    @Before
    public void before() {
        user = new User();

        user.setUsername("buenos");
        user.setPassword("123456");
        user.setPasswordConfirmation("123456");

        if (userRepository.findByUsername("buenos") != null) {
            userRepository.removeByUsername("buenos");
        }
    }

    @Test
    public void registerUserWithCorrectInfo() throws UsernameExistsException {
        userService.registerNewUserAccount(user);

        Assert.assertSame(userRepository.findByUsername(user.getUsername()).getUsername(), user.getUsername());

        userRepository.removeByUsername("buenos");

    }

    @Test(expected = ConstraintViolationException.class)
    public void registerUserWithPasswordNotMatching() throws UsernameExistsException {
        user.setPassword("000000");

        userService.registerNewUserAccount(user);

    }


}
