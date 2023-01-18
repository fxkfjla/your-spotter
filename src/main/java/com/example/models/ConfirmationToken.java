package com.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document("confirmation_tokens")
public class ConfirmationToken
{
    /**
     * 
     * @param token unique UUID string
     * @param createdAt time the token is created
     * @param expiresAt time the token expires
     * @param user for whom the token is created
     */
    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, User user)
    {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
        this.userId = user.getId();
    }

    @Id
    private int id;
    @Field
    private String token;
    @Field
    private LocalDateTime createdAt;
    @Field
    private LocalDateTime expiresAt;
    @Field
    private LocalDateTime confirmedAt;
    @Transient
    private User user;
    @Field
    private int userId;
}
