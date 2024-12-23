package com.callcenter.AuthService.Repositories;

import com.callcenter.AuthService.Entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>
{
    Optional<RoleEntity> findByValue(String value);
}
