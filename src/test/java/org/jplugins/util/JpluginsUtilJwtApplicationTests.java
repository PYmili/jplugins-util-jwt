package org.jplugins.util;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.jplugins.util.jwt.JwtClaimsUtil;
import org.jplugins.util.jwt.JwtPayload;
import org.jplugins.util.jwt.JwtUtil;
import org.jplugins.util.jwt.JwtBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpluginsUtilJwtApplicationTests {

    @Resource
    private JwtUtil util;

    @Resource
    private JwtClaimsUtil claimsUtil;

    @Test
    void contextLoads() {
        // System.out.println(util.generationSecretKey(32));
        String jwt = util.generation(JwtBuilder.builder()
                        .subject("test")
                        .payload(new JwtPayload(1L, "user"))
                        .build());
        Claims parse = util.parse(jwt);
        System.out.println(jwt);
        System.out.println(parse);
        System.out.println(claimsUtil.getIssuedAtString(parse));
        System.out.println(claimsUtil.getExpirationString(parse));
        System.out.println(util.isTokenValid(jwt));
    }

}
