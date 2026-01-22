package org.jplugins.util.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
