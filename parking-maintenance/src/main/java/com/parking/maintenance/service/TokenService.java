package com.parking.maintenance.service;

import com.parking.maintenance.constants.AppConstants;
import com.parking.maintenance.constants.TokenConstants;
import com.parking.maintenance.entity.User;
import com.parking.maintenance.exception.TokenMalformedException;
import com.parking.maintenance.exception.TokenMissingException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token.validity}")
    private long tokenValidity;

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUserId());
        claims.put(TokenConstants.ROLES.getValue(), user.getRolesAsSet());
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + tokenValidity;
        Date exp = new Date(expMillis);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public void validateToken(final String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new TokenMalformedException(TokenConstants.INVALID_JWT_SIGNATURE.getValue());
        } catch (MalformedJwtException ex) {
            throw new TokenMalformedException(TokenConstants.INVALID_JWT_TOKEN.getValue());
        } catch (ExpiredJwtException ex) {
            throw new TokenMalformedException(TokenConstants.EXPIRED_JWT_TOKEN.getValue());
        } catch (UnsupportedJwtException ex) {
            throw new TokenMalformedException(TokenConstants.UNSUPPORTED_JWT_TOKEN.getValue());
        } catch (IllegalArgumentException ex) {
            throw new TokenMissingException(TokenConstants.JWT_STRING_EMPTY.getValue());
        }
    }

    public User getUserDetailsFromToken(final String token) {
        User user = null;
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            user = new User();
            user.setUserId(body.getSubject());
            List<String> roles = (List<String>) body.get(TokenConstants.ROLES.getValue());
            user.setRoles(
                    roles
                    .stream()
                    .collect(Collectors.joining(AppConstants.COMMA_DELIMITER.getValue()))
            );
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return user;
    }


}
