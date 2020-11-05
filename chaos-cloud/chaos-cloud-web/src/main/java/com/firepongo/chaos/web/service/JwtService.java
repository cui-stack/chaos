package com.firepongo.chaos.web.service;

import com.firepongo.chaos.app.login.manage.JwtData;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author G.G
 * @date 2020/1/8 18:41
 */
@Component
public class JwtService {
    @Value("${app.sign:chaos}")
    private String sign;

    public String createToken(String userMu) {
        long now = System.currentTimeMillis();
        long exp = now + 1000 * 60 * 60 * 2;
        JwtBuilder builder = Jwts.builder().setId("888").setSubject("G.G")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, sign)
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
