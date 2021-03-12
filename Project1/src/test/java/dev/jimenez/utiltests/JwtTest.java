package dev.jimenez.utiltests;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.jimenez.utils.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtTest {

    @Test
    void create_jwt(){
        String jwt = JwtUtil.generate(1,"manager","nathanj");
        System.out.println(jwt);
    }

    @Test
    void decode_jwt(){
        DecodedJWT jwt = JwtUtil.isValidJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoibWFuYWdlciIsInVzZXJuYW1lIjoibmF0aGFuaiJ9.RZ7is-Wc73SgbkyBf9iMCJcgXy0Z7F49n3VJEe-H22c");
        String role = jwt.getClaim("role").asString();
        Assertions.assertEquals("manager",role);
        System.out.println(role);
    }


}
