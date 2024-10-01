package com.callcenter.AuthService.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@ToString
@Table(name = "account_register_type")
public class RegisterTypeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    private String value;

    public RegisterTypeEntity()
    {
        this.id = null;
        this.name = null;
        this.value = null;
    }

    public RegisterTypeEntity(Integer id)
    {
        this.id = id;
        this.name = null;
        this.value = null;
    }

    public RegisterTypeEntity(Integer id, String name)
    {
        this.id = id;
        this.name = name;
        this.value = null;
    }

    public RegisterTypeEntity(Integer id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
}
