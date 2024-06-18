package org.example.ecom_userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.ecom_userservice.dtos.LoginRequestDTO;
import org.example.ecom_userservice.dtos.LoginResponseDTO;
import org.example.ecom_userservice.dtos.SignUpRequestDTO;
import org.example.ecom_userservice.exceptions.UserAlreadyExistException;
import org.example.ecom_userservice.models.User;
import org.example.ecom_userservice.security.SecurityConfig;
import org.example.ecom_userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Qualifier("authorizationServerSecurityFilterChain")
    @Autowired
    private SecurityFilterChain securityFilterChain;

    @RequestMapping("/test")
    public ResponseEntity<String> testAPI(){
        ResponseEntity responseEntity = new ResponseEntity("Working fine", HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) throws UserAlreadyExistException, JsonProcessingException {
        String name = signUpRequestDTO.getName();
        String email = signUpRequestDTO.getEmail();
        String password = signUpRequestDTO.getPassword();
        User user = userService.signUp(name, email, password);
        ResponseEntity responseEntity = new ResponseEntity(user, HttpStatus.OK);
        return responseEntity;
    }
}
