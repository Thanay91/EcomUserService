package org.example.ecom_userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ecom_userservice.models.Role;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@JsonDeserialize
@NoArgsConstructor
public class CoustomGrantAuthorities implements GrantedAuthority {
    private String authority;

    public CoustomGrantAuthorities(Role role){
        this.authority = role.getName();
    }

}
