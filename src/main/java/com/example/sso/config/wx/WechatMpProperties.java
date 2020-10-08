package com.example.sso.config.wx;

import com.ag.core.commons.util.StringUtils;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author AGbetrayal
 * @date 2019/6/27 14:28
 */
@Data
@ConfigurationProperties(prefix = "wechat.mp")
public class WechatMpProperties {

//    /**
//     * 设置微信公众号的appid
//     */
//    private String appId;
//
//    /**
//     * 设置微信公众号的app secret
//     */
//    private String secret;
//
//    /**
//     * 设置微信公众号的token
//     */
//    private String token;
//
//    /**
//     * 设置微信公众号的EncodingAESKey
//     */
//    private String aesKey;

    private boolean enabled;
    /**
     * 设置微信公众号的appid
     */
    private String appId;

    /**
     * 设置微信公众号的app secret
     */
    private String secret;

    /**
     * 设置微信公众号的token
     */
    private String token;

    /**
     * 设置微信公众号的EncodingAESKey
     */
    private String aesKey;

    @NestedConfigurationProperty
    @Autowired
    private Authentication authentication;
    // private Authentication authentication = new Authentication();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Data
    @Component("authentication")
    public static class Authentication {

        /**
         * 微信登陆回调
         */
        @NotNull
        private String callbackUrl;

        private String callHost;

        /**
         * 一个随机的字符串，非必填，微信扫码回调的时候会带回此参数，此参数可用于防止csrf攻击（跨站请求伪造攻击）
         */
        private String state;

        /**
         * @param callbackUrl the callbackUrl to set
         */
        public void setCallbackUrl(String callbackUrl) {
            if (!StringUtils.startsWith(callbackUrl, "/")) {
                callbackUrl = "/" + callbackUrl;
            }
            this.callbackUrl = callbackUrl;
        }
    }



}
