package com.callcenter.AuthService.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

import java.io.Serializable;

@Entity
@IdClass(value = BannedPermissionEntity.BannedPermissionEntityId.class)
@Table(name = "banned_permission")
@Getter
@Setter
public class BannedPermissionEntity
{
    @Getter
    @Setter
    @Data
    public class BannedPermissionEntityId implements Serializable
    {
        protected String account_id;
        private Integer permission_id;
    }

    @Id
    private String account_id;
    @Id
    private Integer permission_id;
    private Integer role_id;

    @Override
    public String toString() {
        return "BannedPermissionEntity{" +
                "account_id='" + account_id + '\'' +
                ", permission_id=" + permission_id +
                ", role_id=" + role_id +
                '}';
    }
}
