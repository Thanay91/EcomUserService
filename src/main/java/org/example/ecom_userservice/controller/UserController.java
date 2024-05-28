package org.example.ecom_userservice.controller;

import org.example.ecom_userservice.dtos.SignUpRequestDTO;
import org.example.ecom_userservice.exceptions.UserAlreadyExistException;
import org.example.ecom_userservice.models.User;
import org.example.ecom_userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public ResponseEntity<String> testAPI(){
        ResponseEntity responseEntity = new ResponseEntity("Working fine", HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) throws UserAlreadyExistException {
        String name = signUpRequestDTO.getName();
        String email = signUpRequestDTO.getEmail();
        String password = signUpRequestDTO.getPassword();
        User user = userService.signUp(name, email, password);
        ResponseEntity responseEntity = new ResponseEntity(user, HttpStatus.OK);
        return responseEntity;
    }
}
