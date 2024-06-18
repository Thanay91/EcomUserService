package org.example.ecom_userservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ecom_userservice.dtos.SignUpEventDTO;
import org.example.ecom_userservice.exceptions.UserAlreadyExistException;
import org.example.ecom_userservice.models.User;
import org.example.ecom_userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    public User signUp(String name, String email, String password) throws UserAlreadyExistException, JsonProcessingException {
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

        SignUpEventDTO signUpEventDTO = new SignUpEventDTO();
        signUpEventDTO.setTo(savedUser.getEmail());
        signUpEventDTO.setFrom("thanay3691@gmail.com");
        signUpEventDTO.setSubject("Welcome to testing!");
        signUpEventDTO.setBody("Welcome " + savedUser.getName() + " .................!");



        kafkaTemplate.send("signUpEventTopic",
                objectMapper.writeValueAsString(signUpEventDTO));
        return savedUser;
    }






}
