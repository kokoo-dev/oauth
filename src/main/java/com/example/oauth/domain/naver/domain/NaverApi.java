package com.example.oauth.domain.naver.domain;

import com.example.oauth.global.config.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:oauth.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "naver")
@Getter
@Setter
public class NaverApi {
    private String clientId;
    private String clientSecret;
    private String authHost;
    private String authPath;
    private String tokenPath;
    private String responseType;
    private String redirectUri;
    private String grantTypeIssue;
    private String grantTypeDelete;
    private String serviceProvider;
}
