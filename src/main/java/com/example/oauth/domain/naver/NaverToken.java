package com.example.oauth.domain.naver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class NaverToken {
    private String code;
    private String state;
}
