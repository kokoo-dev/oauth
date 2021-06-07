package com.example.oauth.domain.google;

import com.example.oauth.config.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:oauth.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "google")
@Getter
@Setter
public class GoogleApi {
    private String clientId;
    private String clientPassword;
    private String authHost;
    private String authPath;
    private String apiHost;
    private String tokenPath;
    private String redirectUri;
    private String responseType;
    private String scope;
    private String accessType;
    private String grantTypeIssue;
}
