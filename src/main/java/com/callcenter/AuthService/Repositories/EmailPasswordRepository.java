package com.callcenter.AuthService.Repositories;

import com.callcenter.AuthService.Entities.EmailPasswordAuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailPasswordRepository extends JpaRepository<EmailPasswordAuthenticationEntity, String>
{
    Optional<EmailPasswordAuthenticationEntity> findByEmail(String email);
}
