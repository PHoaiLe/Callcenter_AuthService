package com.callcenter.AuthService.Repositories;

import com.callcenter.AuthService.Entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer>
{

}
