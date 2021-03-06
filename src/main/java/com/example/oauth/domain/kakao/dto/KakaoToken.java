package com.example.oauth.domain.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class KakaoToken {
    private String code;
    private String accessToken;
}
