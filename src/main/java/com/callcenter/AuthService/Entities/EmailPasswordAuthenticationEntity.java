package com.callcenter.AuthService.Entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Entity
@Data
@Table(name = "email_password_authentication")
@Getter
@Setter
public class EmailPasswordAuthenticationEntity
{
    private EmailPasswordAuthenticationEntity()
    {
        this.id = "";
        this.email = "";
        this.password = "";
    }

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    protected String id;
    @NonNull
    protected String email;
    @NonNull
    protected String password;

    public static EmailPasswordAuthenticationEntity getInstance(String email, String password)
    {
        EmailPasswordAuthenticationEntity entity = new EmailPasswordAuthenticationEntity();
        UUID uuid = UUID.randomUUID();

        String id = uuid.toString();

        entity.setId(id);
        entity.setEmail(email);
        entity.setPassword(password);

        return entity;
    }
}
