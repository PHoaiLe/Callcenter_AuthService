package com.callcenter.AuthService.Repositories;

import com.callcenter.AuthService.Entities.AccountStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatusEntity, Integer>
{

}
