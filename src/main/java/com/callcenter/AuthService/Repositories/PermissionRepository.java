package com.callcenter.AuthService.Repositories;

import com.callcenter.AuthService.Entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer>
{

}
