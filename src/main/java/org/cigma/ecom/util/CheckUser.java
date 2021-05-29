package org.cigma.ecom.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckUser {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public String getUsername(String headerAuthorization){
        String token = getToken(headerAuthorization);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

    public String getToken(String headerAuthorization){
        return headerAuthorization.substring(7);
    }
}
