package com.example.sso.config.wx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author AGbetrayal
 * @date 2019/6/27 14:28
 */
@Data
@ConfigurationProperties(prefix = "wechat.mp")
public class WechatMpProperties {

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
}
