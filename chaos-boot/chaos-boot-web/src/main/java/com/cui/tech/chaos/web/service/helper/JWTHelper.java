package com.cui.tech.chaos.web.service.helper;

import com.cui.tech.chaos.model.login.JwtData;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author G.G
 * @date 2020/1/8 18:41
 */
@Component
public class JWTHelper {

    public String createToken(String userMu) {
        long now = System.currentTimeMillis();
        long exp = now + 1000 * 60 * 60 * 2;
        JwtBuilder builder = Jwts.builder().setId("888").setSubject("G.G")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "chaos")
                .setExpiration(new Date(exp))
                .claim("userMu", userMu);
        return builder.compact();
    }

    public JwtData getJwtData(String token) {
        Claims claims =
                Jwts.parser().setSigningKey("chaos").parseClaimsJws(token).getBody();
        return new JwtData((String) claims.get("userMu"), claims.getExpiration());
    }

    public String getUserMuInJwtData(String token) {
        try {
            return getJwtData(token).getUserMu();
        } catch (ExpiredJwtException e) {
            return "";
        }
    }
}
