package com.callcenter.AuthService.Repositories;

import com.callcenter.AuthService.Entities.BannedPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannedPermissionRepository extends JpaRepository<BannedPermissionEntity, BannedPermissionEntity.BannedPermissionEntityId>
{

}
