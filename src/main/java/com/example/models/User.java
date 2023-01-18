package com.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.utility.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@Document("users")
public class User implements UserDetails
{
    /**
     * 
     * @param email
     * @param password
     * @param role USER / ADMIN
     */
    public User(String email, String password, UserRole role)
    {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * return user roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return email;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    @Id
    private int id;
    @Field
    private String email;
    @Field
    private String password;
    @Field
    private UserRole role;
    @Field
    // flag to check if user account is locked
    private Boolean locked = false;
    @Field
    // flag to check if user confirmed email
    private Boolean enabled = false;
    @Field
    // id session that is assigned when user logs in
    private String sessionId;
    @Field
    private int loyalityPoints;
}