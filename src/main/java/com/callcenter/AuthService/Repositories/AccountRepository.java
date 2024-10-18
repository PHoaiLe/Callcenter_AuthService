package com.callcenter.AuthService.Repositories;

import com.callcenter.AuthService.Entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String>
{
    //JPQL
    @Query(value = "SELECT a " +
            "FROM AccountEntity a WHERE a.auth_info_id = :auth_info_id")
    Optional<AccountEntity> findByAuthInfoId(@Param(value = "auth_info_id") String auth_info_id);
}
