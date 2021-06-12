package com.example.oauth.domain.kakao.domain;

import com.example.oauth.global.config.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:oauth.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "kakao")
@Getter
@Setter
public class KakaoApi {
    private String authHost;
    private String apiHost;
    private String authorizePath;
    private String tokenPath;
    private String logoutPath;
    private String apiKey;
    private String redirectUri;
    private String responseType;
    private String grantTypeIssue;
    private String authType;
}
