package org.example.ecom_userservice.security.services;


import org.example.ecom_userservice.models.User;
import org.example.ecom_userservice.repositories.UserRepository;
import org.example.ecom_userservice.security.models.CoustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CoustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("This User is not registered yet");
        }
        UserDetails userDetails = new CoustomUserDetails(optionalUser.get());
        return userDetails;
    }
}
