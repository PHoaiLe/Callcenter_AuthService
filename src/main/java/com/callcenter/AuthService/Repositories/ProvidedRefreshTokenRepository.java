package com.callcenter.AuthService.Repositories;

import com.callcenter.AuthService.Entities.ProvidedRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvidedRefreshTokenRepository extends JpaRepository<ProvidedRefreshTokenEntity, String>
{

}
