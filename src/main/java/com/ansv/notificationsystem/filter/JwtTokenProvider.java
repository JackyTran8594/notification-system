package com.ansv.notificationsystem.filter;

import com.ansv.notificationsystem.constants.JwtExceptionEnum;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    public String validateError = new String();

    @Value("${app.jwtSecret}")
    private String JWT_SECRET;

    @Value("${app.tokenValidity}")
    private String JWT_TOKEN_VALIDITY;


    public Date getJwtTokenValidity() {
        Date now = new Date();
        return new Date(now.getTime() + JWT_TOKEN_VALIDITY);
    }

    public String getUUID(String token) {
        Claims claims = Jwts.parser().setSigningKey((JWT_SECRET)).parseClaimsJws(token).getBody();
//        Claims claims = Jwts.parser().parseClaimsJws(token).getBody();
        return claims.get("uuid").toString();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey((JWT_SECRET)).parseClaimsJws(token).getBody();
//        Claims claims = Jwts.parser().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String generateToken(Authentication authentication, String role, List<String> permissions) {
        // using LDAP
//        LdapUserDetailsImpl user = (LdapUserDetailsImpl)authentication.getPrincipal();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expriryDate = new Date(now.getTime() + JWT_TOKEN_VALIDITY);
        return Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(now).setExpiration(expriryDate).signWith(SignatureAlgorithm.HS512, JWT_SECRET)
//                .claim("role", role).claim("permissions", permissions)
                .compact()
                ;
    }

    public String generateToken(String username, String role, List<String> permissions) {
        Date now = new Date();
        Date expriryDate = new Date(now.getTime() + JWT_TOKEN_VALIDITY);
        return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expriryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
//                .claim("role", role).claim("permissions", permissions)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature", e);
            this.validateError = JwtExceptionEnum.INVALID_JWT_SIGNATURE.getName();
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT Token ");
            this.validateError = JwtExceptionEnum.INVALID_JWT_TOKEN.getName();

        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT Token");
            this.validateError = JwtExceptionEnum.EXPIRED_JWT_TOKEN.getName();

        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT Token");
            this.validateError = JwtExceptionEnum.UNSUPPORT_JWT_TOKEN.getName();

        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty");
            this.validateError = JwtExceptionEnum.JWT_CLAIMS_EMPTY.getName();

        }
        return false;
    }


}
