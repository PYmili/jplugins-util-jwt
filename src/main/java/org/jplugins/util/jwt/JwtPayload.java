package org.jplugins.util.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于Jwt Token Payload（载荷）的自定义数据
 * @author PYmili
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtPayload {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

}
