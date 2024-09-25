package com.callcenter.AuthService.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Entity
@Table(name = "role")
@Data
@Getter
@Setter
public class RoleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    private String value;

    private RoleEntity()
    {
        this.id = null;
        this.name = null;
        this.value = null;
    }

    private RoleEntity(String name, String value)
    {
        this.id = null;
        this.name = name;
        this.value = value;
    }

    private RoleEntity(Integer id, String name, String value)
    {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public static RoleEntity getInstance(String name, String value)
    {
        RoleEntity entity = new RoleEntity(name, value);

        return entity;
    }

}
