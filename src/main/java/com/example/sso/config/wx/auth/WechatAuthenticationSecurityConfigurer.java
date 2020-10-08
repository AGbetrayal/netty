package com.example.sso.config.wx.auth;


import com.ag.core.authentication.api.PostAuthenticationHandler;
import com.ag.core.authentication.api.UserPrincipal;
import com.example.sso.config.wx.WechatMpProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 微信认证配置
 *
 * @author zhengaiguo
 * @date 2018年2月8日上午11:38:35
 */
@RequiredArgsConstructor
//@Component("wechatAuthenticationSecurityConfigurer")
public class WechatAuthenticationSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    /*private final WxMpService wxMpService;

    private final WechatMpProperties.Authentication authentication;

    // private final UserPrincipalService<UserPrincipal,UserPrincipal> wechatUserPrincipalService;
    private final PostAuthenticationHandler<UserPrincipal, WxMpUser> authenticationHandler;*/

    private  WxMpService wxMpService;

    private  WechatMpProperties.Authentication authentication;

    // private final UserPrincipalService<UserPrincipal,UserPrincipal> wechatUserPrincipalService;
    private  PostAuthenticationHandler<UserPrincipal, WxMpUser> authenticationHandler;

    public WechatAuthenticationSecurityConfigurer(WxMpService wxMpService, PostAuthenticationHandler<UserPrincipal, WxMpUser> authenticationHandler,
                                                  WechatMpProperties.Authentication authentication ) {
        this.wxMpService = wxMpService;
        this.authentication = authentication;
        this.authenticationHandler = authenticationHandler;
    }


    /**
     * 此方法是将 WechatAuthenticationProvider 注册到spring security的filter 中
     */
    @Override
    public void configure(HttpSecurity http) {
        WeiXinCallbackAuthenticationFilter filter = new WeiXinCallbackAuthenticationFilter(wxMpService, authentication.getCallbackUrl(), authentication.getState());
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        WeiXinAuthenticationProvider provider = new WeiXinAuthenticationProvider(authenticationHandler);
        http.authenticationProvider(provider).addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
