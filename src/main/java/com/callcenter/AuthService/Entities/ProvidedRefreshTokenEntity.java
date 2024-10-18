package com.callcenter.AuthService.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "provided_refresh_token")
@Getter
@Setter
public class ProvidedRefreshTokenEntity
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String token;
    private String account_id;
    private Date created_at;
    private Date expired_at;
    private Date used_at;

    private ProvidedRefreshTokenEntity() {}

    private ProvidedRefreshTokenEntity(@NonNull String id, @NonNull String token, @NonNull String account_id,
                                       @NonNull Date created_at, @NonNull Date expired_at)
    {
        this.id = id;
        this.token = token;
        this.account_id = account_id;
        this.created_at = created_at;
        this.expired_at = expired_at;
        this.used_at = null;
    }

    public static ProvidedRefreshTokenEntity getInstance(@NonNull String token, @NonNull String account_id,
                                                  @NonNull Date created_at, @NonNull Date expired_at)
    {
        String uuid = UUID.randomUUID().toString();

        return new ProvidedRefreshTokenEntity(uuid, token, account_id, created_at, expired_at);
    }
}
