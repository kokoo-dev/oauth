package com.example.oauth.domain.facebook;

import com.example.oauth.config.YamlPropertySourceFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:oauth.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "facebook")
@Getter
@Setter
public class FacebookApi {
    private String clientId;
    private String clientSecret;
    private String authHost;
    private String authPath;
    private String tokenHost;
    private String tokenPath;
    private String redirectUri;
    private String debugTokenPath;
    private String permissionsPath;
}
