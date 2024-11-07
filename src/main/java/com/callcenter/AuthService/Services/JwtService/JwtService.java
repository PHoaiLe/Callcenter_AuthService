package com.callcenter.AuthService.Services.JwtService;

import com.callcenter.AuthService.DTO.JWT.ExtraClaims.JwtClaimsFromAccountEntityProvider;
import com.callcenter.AuthService.DTO.JWT.JwtClaimsProvider;
import com.callcenter.AuthService.DTO.JWT.JwtGeneratedToken;
import com.callcenter.AuthService.Entities.AccountEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

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
    public JwtGeneratedToken generateIndividualToken(String secretKey, long expiration, JwtClaimsProvider extraClaims)
    {
        long currentTimeMillis = System.currentTimeMillis();

        Date issuedAt = new Date(currentTimeMillis);
        Date expiredAt = new Date(currentTimeMillis + expiration);

        String token = Jwts.builder().signWith(getSignInKey(secretKey), SignatureAlgorithm.HS256)
                .setClaims(extraClaims.toMap())
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .compact();

        return JwtGeneratedToken.builder()
                .token(token)
                .issuedAt(issuedAt)
                .expiredAt(expiredAt)
                .build();
    }

    public JwtGeneratedToken generateAccessToken(AccountEntity entity)
    {
        JwtClaimsFromAccountEntityProvider jwtClaims = new JwtClaimsFromAccountEntityProvider(entity);

        return generateIndividualToken(this.accessTokenSecretKey, Long.valueOf(this.accessTokenExpiration).longValue(), jwtClaims);
    }

    public JwtGeneratedToken generateRefreshToken(AccountEntity entity)
    {
        JwtClaimsFromAccountEntityProvider jwtClaims = new JwtClaimsFromAccountEntityProvider(entity);

        return generateIndividualToken(this.refreshTokenSecretKey, Long.valueOf(this.refreshTokenExpiration).longValue(), jwtClaims);
    }

    public Claims getClaims(String token, String secret) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException
    {
        return Jwts.parserBuilder().setSigningKey(getSignInKey(secret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <RETURN_TYPE> RETURN_TYPE getAccessTokenClaims(String token, Function<Claims, RETURN_TYPE> claimsResolver) throws
            ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException
    {
        Claims claims = this.getClaims(token, this.accessTokenSecretKey);
        return claimsResolver.apply(claims);
    }

    public <RETURN_TYPE> RETURN_TYPE getRefreshTokenClaims(String token, Function<Claims, RETURN_TYPE> claimsResolver) throws
            ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException
    {
        Claims claims = this.getClaims(token, this.refreshTokenSecretKey);
        return claimsResolver.apply(claims);
    }
}
