package com.example.oauth.domain.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class Kakao {
    private String code;
    private String accessToken;
}
