package com.callcenter.AuthService.Services;

import com.callcenter.AuthService.DTO.Endpoint.APIEndPoint;
import com.callcenter.AuthService.Entities.PermissionEntity;
import com.callcenter.AuthService.Repositories.PermissionRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService
{
    private PermissionRepository permissionRepository;
    private EntityManager entityManager;

//    private static final String IDENTIFY_PERMISSION_QUERY = "SELECT pms.id, pms.name, pms.value, pms.endpoint\n" +
//            "FROM %s reft JOIN PermissionEntity pms ON (pms.endpoint = :endpoint AND reft.permission_id = pms.id)\n" +
//            "\tLEFT JOIN BannedPermissionEntity bpms ON (bpms.account_id = :account_id AND pms.id = bpms.permission_id)\n" +
//            "WHERE bpms.permission_id IS NULL";

    //Native query
    private static final String IDENTIFY_PERMISSION_QUERY = "SELECT pms.id, pms.name, pms.value, pms.endpoint\n" +
            "FROM %s reft JOIN permission pms ON (pms.endpoint = :endpoint AND reft.permission_id = pms.id)\n" +
            "\tLEFT JOIN banned_permission bpms ON (bpms.account_id = :account_id AND pms.id = bpms.permission_id)\n" +
            "WHERE bpms.permission_id IS NULL";

    @Autowired
    public PermissionService(PermissionRepository permissionRepository, EntityManager entityManager)
    {
        this.permissionRepository = permissionRepository;
        this.entityManager = entityManager;
    }

    public PermissionEntity verifyPermissionByRoleId(String referTable, String accountId, APIEndPoint endPoint) throws Exception
    {
        return (PermissionEntity) entityManager.createNativeQuery(IDENTIFY_PERMISSION_QUERY.formatted(referTable), PermissionEntity.class)
                .setParameter("endpoint", endPoint.toDatabaseEndpointAttribute())
                .setParameter("account_id", accountId)
                .getSingleResult();
    }
}
