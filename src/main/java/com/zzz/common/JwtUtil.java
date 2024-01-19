package com.zzz.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author digediao
 * @version 1.0
 * @description TODO
 * @Date 2024/1/19 10:04
 */

public class JwtUtil {
    /*有效期*/
    private static final Long EXPIRE_TIME = 60 * 60L;   // 一小时

    /*密钥*/
    private static final String JWT_KEY = "digediao";

    public static String getUUID() {
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }

    /*生成jwt*/
    public static String createJWT(String subject) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, null, getUUID());
        return jwtBuilder.compact();
    }

    public static String createJWT(String subject, Long ttlSeconds) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, ttlSeconds, getUUID());
        return jwtBuilder.compact();
    }

    public static String createJWT(String subject, Long ttlMillis, String uuid) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, ttlMillis, uuid);
        return jwtBuilder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlSeconds, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);
        if (ttlSeconds == null) {
            ttlSeconds = EXPIRE_TIME * 1000;
        }
        long expMillis = nowMillis + ttlSeconds * 1000;
        Date expireDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("digediao")
                .setIssuedAt(date)
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expireDate);
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static Claims parseJWT(String token) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
        String token = "123456";
        String jwt = createJWT("digediao", 4L, token);
        Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5YzAxMGRlYTQ5MmE0MDVlYjI5ZTVjNjIzOTQ0ZDhlMSIsInN1YiI6IjEiLCJpc3MiOiJkaWdlZGlhbyIsImlhdCI6MTcwNTY1MTYzMCwiZXhwIjoxNzA1NzExNjMwfQ.VfpSBuB-7ngMG1YA1oPgDicD_jtWU8x0A3tmTJz_y6U");
        System.out.println("jwt:" + jwt);
        System.out.println("claims:" + claims);
    }

}
