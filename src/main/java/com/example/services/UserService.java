package com.example.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.models.ConfirmationToken;
import com.example.models.User;
import com.example.repositories.UserRepository;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService
{
    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public User getByEmail(String email)
    {
        Optional<User> user = userRepository.findByEmail(email);

        if(!user.isPresent())
        {
            // TODO: Handle exception
            throw new IllegalStateException("user not found");
        }

        return user.get();
    }

    public boolean compare(String email, String password)
    {
        Optional<User> user = userRepository.findByEmail(email);

        if(!user.isPresent())
        {
            // TODO: Handle exception
            throw new IllegalStateException("user not found");
        }

        String hashedPassword = user.get().getPassword();

        return passwordEncoder.matches(password, hashedPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return userRepository.findByEmail(email).orElseThrow
        (
            ()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email))
        );
    }

    public String signUpUser(User user)
    {
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());

        if(foundUser.isPresent())
        {
            user.setEnabled(foundUser.get().getEnabled());
            user.setLocked(foundUser.get().getLocked());
            return "confirmation email resent";
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setId(userRepository.maxId() + 1);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
        tokenService.save(confirmationToken);

        return token;
    }

    public void enableUser(int id)
    {
        userRepository.enableUser(id);
    }

    public User findBySessionId(String sessionId)
    {
        Optional<User> user = userRepository.findBySessionId(sessionId);

        if(!user.isPresent())
        {
            // TODO: handle exception
            throw new IllegalStateException("User with " + sessionId + " session id not found");
        }

        return user.get();
    }

    public void changePassword(String newPassword, String email)
    {
        newPassword = passwordEncoder.encode(newPassword);
        this.userRepository.updatePassword(newPassword, email);
    }
    
    public void save(User user)
    {
        userRepository.save(user);
    }

    private final UserRepository userRepository;
    private final ConfirmationTokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
}