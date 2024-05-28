package org.example.ecom_userservice.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.example.ecom_userservice.dtos.UserDTO;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    private String hashedPassword;

    //U  :  R
    //1  :  M
    //M  :  1
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    private boolean isEmailVerified;


    public static UserDTO from(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.email);
        userDTO.setEmailVerified(user.isEmailVerified);
        userDTO.setRoles(user.roles);
        userDTO.setName(user.name);
        return userDTO;
    }

}
