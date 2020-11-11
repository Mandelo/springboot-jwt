package com.example.jwt.realm;

import com.example.jwt.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomRealm extends AuthorizingRealm {

    String base64Secret = "Z3VveGl1emhpRXJiYWRhZ2FuZ1dpbnNwYWNlVjMuMA==";

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String tokenStr = (String) authenticationToken.getCredentials();
        String token = tokenStr.substring(7);
        String username = JwtUtil.getUsername(token, base64Secret);

        if (username == null) {
            throw new AuthenticationException("token invalid");
        }




        return new SimpleAuthenticationInfo(token,token,"custom_realm");
    }
}
