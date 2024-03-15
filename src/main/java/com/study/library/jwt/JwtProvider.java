package com.study.library.jwt;


import com.study.library.entity.User;
import com.study.library.security.PrincipalUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {
    
//    -------------------------------------------------------------------------- 고정
    private final Key key;
    
    // 생성될 때 야놀에서 secret 값을가져온다
    public JwtProvider(@Value("${jwt.secret}") String secret) {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
//    -------------------------------------------------------------------
    
    public String generateToken(User user) {

        int userId = user.getUserId();
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // 만료기간 = 현재 날짜 시간을 가지고와서 하루(24시간) 더해라
        Date expireDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));

        // 토큰 만들기
        String accessToken = Jwts.builder()
                .claim("userId", userId)                    // key, value
                .claim("username", username)                // key, value
                .claim("authorities", authorities)          // 권한
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)       // 암호화
                .compact();

        return accessToken;
    }

}
