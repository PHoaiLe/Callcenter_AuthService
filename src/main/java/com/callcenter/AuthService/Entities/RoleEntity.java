package com.callcenter.AuthService.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    private String refer_table;

    private RoleEntity()
    {
        this.id = null;
        this.name = null;
        this.value = null;
        this.refer_table = null;
    }

    private RoleEntity(String name, String value)
    {
        this.id = null;
        this.name = name;
        this.value = value;
        this.refer_table = null;
    }

    private RoleEntity(Integer id, String name, String value)
    {
        this.id = id;
        this.name = name;
        this.value = value;
        this.refer_table = null;
    }

    public static RoleEntity getInstance(String name, String value)
    {
        RoleEntity entity = new RoleEntity(name, value);

        return entity;
    }

}
