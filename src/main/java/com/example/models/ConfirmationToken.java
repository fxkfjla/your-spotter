package com.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document
public class ConfirmationToken
{
    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, User user)
    {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    @Id
    private Long id;
    @Field
    @NonNull
    private String token;
    @Field
    @NonNull
    private LocalDateTime createdAt;
    @Field
    @NonNull
    private LocalDateTime expiresAt;
    @Field
    @NonNull
    private LocalDateTime confirmedAt;
    @Field
    private User user;
}
