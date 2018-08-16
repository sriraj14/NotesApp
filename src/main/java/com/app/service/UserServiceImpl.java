package com.app.service;


import com.app.exceptions.UsernameExistsException;
import com.app.model.User;
import com.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public User registerNewUserAccount(User user) throws UsernameExistsException {

        if (usernameExist(user.getUsername())) {
            throw new UsernameExistsException(
                    "There is an account with that username: "
                            + user.getUsername());
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setPasswordConfirmation(user.getPasswordConfirmation());
        newUser.setRole("ROLE_USER");
        return userRepository.save(newUser);

    }

    private boolean usernameExist(String username) {
        User user = userRepository.findByUsername(username);

        return user != null;


    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
