package com.callcenter.AuthService.Repositories;

import com.callcenter.AuthService.Entities.EmailPasswordAuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailPasswordRepository extends JpaRepository<EmailPasswordAuthenticationEntity, String>
{

}
