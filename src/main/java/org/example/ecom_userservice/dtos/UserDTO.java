package org.example.ecom_userservice.dtos;


import lombok.Getter;
import lombok.Setter;
import org.example.ecom_userservice.models.Role;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private List<Role> roles;
    private boolean isEmailVerified;
}
