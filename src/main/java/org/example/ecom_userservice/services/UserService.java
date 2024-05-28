package org.example.ecom_userservice.services;

import org.example.ecom_userservice.exceptions.UserAlreadyExistException;
import org.example.ecom_userservice.models.User;
import org.example.ecom_userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User signUp(String name, String email, String password) throws UserAlreadyExistException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new UserAlreadyExistException("User Already Exist");
        }
        User toBeSavedUser = new User();
        toBeSavedUser.setEmail(email);
        toBeSavedUser.setName(name);
//        toBeSavedUser.setHashedPassword(password);
        toBeSavedUser.setHashedPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(toBeSavedUser);
        return savedUser;
    }




}
