package dev.jimenez.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    public static Algorithm getAlgorithm(){

        String key = System.getenv("JWT_KEY");
        Algorithm a = Algorithm.HMAC256(key);
        return a;

    }

    public static String generate(int id,String role, String username){
        String token = JWT.create()
                .withClaim("ID",id)
                .withClaim("role",role)
                .withClaim("username",username)
                .sign(getAlgorithm());
        return token;
    }

    public static DecodedJWT isValidJWT(String token){
        DecodedJWT jwt = JWT.require(getAlgorithm()).build().verify(token);
        return jwt;
    }

}
