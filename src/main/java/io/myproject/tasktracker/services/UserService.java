package io.myproject.tasktracker.services;

import io.myproject.tasktracker.domain.User;
import io.myproject.tasktracker.exceptions.UsernameAlreadyExistsException;
import io.myproject.tasktracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; // make string password not readable

    // save a new user
    public User saveUser(User newUser){
        try{

            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            // username needs to be unique(do exception)
            newUser.setUsername(newUser.getUsername());

            // password the confirmPassword match
            // we don't persist or show the confirmPassword
            newUser.setConfirmPassword("");

            return userRepository.save(newUser);

        }catch (Exception e){

            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");

        }

    }
}
