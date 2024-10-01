package com.callcenter.AuthService.Repositories;

import com.callcenter.AuthService.Entities.RegisterTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterTypeRepository extends JpaRepository<RegisterTypeEntity, Integer>
{
    Optional<RegisterTypeEntity> findByValue(String value);
}
