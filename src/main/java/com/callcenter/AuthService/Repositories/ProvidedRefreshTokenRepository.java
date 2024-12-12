package com.callcenter.AuthService.Repositories;

import com.callcenter.AuthService.Entities.ProvidedRefreshTokenEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ProvidedRefreshTokenRepository extends JpaRepository<ProvidedRefreshTokenEntity, String>
{
    @Query(value = "SELECT prt " +
            "FROM ProvidedRefreshTokenEntity prt " +
            "WHERE prt.account_id = :account_id " +
            "AND prt.used_at IS null " +
            "AND prt.created_at <= :time AND prt.expired_at >= :time ")
    Optional<ProvidedRefreshTokenEntity> findLatestProvidedRefreshToken(@Param(value = "account_id") @NonNull String account_id,
                                                                        @Param(value = "time") @NonNull Date time);
}
