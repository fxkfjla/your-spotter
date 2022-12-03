package com.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
// @EqualsAndHashCode
@NoArgsConstructor
@Document("users")
public class User implements UserDetails
{
    public User(String email, String password, UserRole role)
    {
        this.id = idCounter;
        ++idCounter;

        this.email = email;
        this.password = password;
        this.role = role;
    }

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
    private Long id;
    @Transient
    private static Long idCounter = 0L;
    @Field
    private String email;
    @Field
    private String password;
    @Field
    private UserRole role;
    @Field
    private Boolean locked;
    @Field
    private Boolean enabled;
}