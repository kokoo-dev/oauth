package com.example.oauth.domain.google.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class GoogleToken {
    private String code;
    private String scope;
    private String authuser;
    private String prompt;
}
