package com.callcenter.AuthService.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "account")
@Getter
@Setter
public class AccountEntity
{
    private AccountEntity()
    {
        this.id = "";
        this.auth_info_id = "";
        this.status = -1;
        this.register_type = -1;
        this.created_at = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    protected String id;

    @NonNull
    protected String auth_info_id;
    @NonNull
    protected Integer status;
    @NonNull
    protected Integer register_type;
    protected Date created_at;
    protected Integer role;

    public static AccountEntity getInstance(String authInfoId, Integer status, Integer registerType, Date createdAt, Integer role)
    {
        AccountEntity newEntity = new AccountEntity();

        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();

        newEntity.setId(id);
        newEntity.setAuth_info_id(authInfoId);
        newEntity.setStatus(status);
        newEntity.setRegister_type(registerType);
        newEntity.setCreated_at(createdAt);
        newEntity.setRole(role);

        return newEntity;
    }

}
