package com.example.oauth.domain.facebook;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FacebookToken {
    private String code;
    private String state;
}
