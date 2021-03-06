package com.example.sso.config.wx.auth;

import com.ag.core.authentication.api.UserPrincipal;
import lombok.var;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * Wechat 认证 Token
 *
 * @author agbetrayal
 * @date 2018年2月8日上午11:14:18
 */
public class WeiXinAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 认证对象
     */
    private final Object principal;

    /**
     * 微信用户，未认证
     */
    public WeiXinAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }

    /**
     * 微信用户，已认证
     *
     * @param principal   认证对象
     * @param authorities 权限
     */
    public WeiXinAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public String getName() {
        var principal = getPrincipal();
        if (principal instanceof UserPrincipal) {
            return ((UserPrincipal) principal).getAccount();
        }
        return super.getName();
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}
