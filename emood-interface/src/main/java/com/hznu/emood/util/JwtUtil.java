package com.hznu.emood.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.hznu.emood.model.User;

import java.util.Date;

public class JwtUtil {

    public static final String SECRET_KEY = "@#'4$adf^&%56!$#32ds'*&2%5&@'3g34%a#4!";//秘钥
    public static final long TOKEN_EXPIRE_TIME = 30 * 60 * 1000; //token过期时间
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 10 * 60 * 1000; //refreshToken过期时间
    private static final String ISSUER = "Emood Server"; //签发人

    public static String getToken(User user) {
        Date date = new Date();
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String uid = String.valueOf(user.getId());
        String token = JWT.create().withAudience(uid)
                .withIssuer(ISSUER) // 签发人
                .withIssuedAt(date) // 签发时间
                .withExpiresAt(new Date(date.getTime() + TOKEN_EXPIRE_TIME)) // 过期时间
                .withClaim("id", uid)
                .withClaim("username", user.getUsername())
                .sign(algorithm);
        return token;
    }

    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //算法
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 从token获取username
     */
    public static String getUsername(String token) {
        try {
            return JWT.decode(token).getClaim("username").asString();
        } catch (Exception ex) {
            throw new RuntimeException("获取用户名失败");
        }
    }

    /**
     * 从token获取id
     */
    public static String getId(String token) {
        try {
            return JWT.decode(token).getClaim("id").asString();
        } catch (Exception ex) {
            throw new RuntimeException("获取用户id失败");
        }
    }


}
