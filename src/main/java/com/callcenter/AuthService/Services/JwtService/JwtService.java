package com.callcenter.AuthService.Services.JwtService;

import com.callcenter.AuthService.DTO.JWT.ExtraClaims.JwtClaimsFromAccountEntity;
import com.callcenter.AuthService.DTO.JWT.JwtExtraClaims;
import com.callcenter.AuthService.DTO.JWT.JwtGeneratedToken;
import com.callcenter.AuthService.Entities.AccountEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService
{
    @Value(value = "${secret.jwt.access_token_secret_key}")
    private String accessTokenSecretKey;
    @Value(value = "${secret.jwt.refresh_token_secret_key}")
    private String refreshTokenSecretKey;
    @Value(value = "${secret.jwt.access_token_expiration}")
    private String accessTokenExpiration;
    @Value(value = "${secret.jwt.refresh_token_expiration}")
    private String refreshTokenExpiration;


    private Key getSignInKey(String secret)
    {
        byte[] decodedString = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(decodedString);
    }
    public JwtGeneratedToken generateIndividualToken(String secretKey, long expiration, JwtExtraClaims extraClaims)
    {
        long currentTimeMillis = System.currentTimeMillis();

        Date issuedAt = new Date(currentTimeMillis);
        Date expiredAt = new Date(currentTimeMillis + expiration);

        String token = Jwts.builder().signWith(getSignInKey(secretKey), SignatureAlgorithm.HS256)
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .setClaims(extraClaims.toMap())
                .compact();

        return JwtGeneratedToken.builder()
                .token(token)
                .issuedAt(issuedAt)
                .expiredAt(expiredAt)
                .build();
    }

    public JwtGeneratedToken generateAccessToken(AccountEntity entity)
    {
        JwtClaimsFromAccountEntity jwtClaims = new JwtClaimsFromAccountEntity(entity);

        return generateIndividualToken(this.accessTokenSecretKey, Long.valueOf(this.accessTokenExpiration).longValue(), jwtClaims);
    }

    public JwtGeneratedToken generateRefreshToken(AccountEntity entity)
    {
        JwtClaimsFromAccountEntity jwtClaims = new JwtClaimsFromAccountEntity(entity);

        return generateIndividualToken(this.refreshTokenSecretKey, Long.valueOf(this.refreshTokenExpiration).longValue(), jwtClaims);
    }
}
