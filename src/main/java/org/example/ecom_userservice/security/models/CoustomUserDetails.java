package org.example.ecom_userservice.security.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NoArgsConstructor;
import org.example.ecom_userservice.models.Role;
import org.example.ecom_userservice.models.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@NoArgsConstructor
public class CoustomUserDetails implements UserDetails {
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<CoustomGrantAuthorities> authorities;
    private Long id;

    public CoustomUserDetails(User user){
        this.username = user.getName();
        this.password = user.getHashedPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired =true;
        this.enabled = true;
        this.id = user.getId();
        List<CoustomGrantAuthorities> authorities = new ArrayList<>();
        for(Role roles: user.getRoles()){
            CoustomGrantAuthorities authority = new CoustomGrantAuthorities(roles);
            authorities.add(authority);
        }
        this.authorities = authorities;
        this.id = user.getId();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    public Long getId(){
        return id;
    }
}
