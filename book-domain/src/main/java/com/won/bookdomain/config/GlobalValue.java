package com.won.bookdomain.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GlobalValue {
    public static final String[] WHITE_LIST = {
            "/**/v3/api-docs/**",
            "/**/configuration/ui",
            "/**/swagger-ui.html",
            "/**/swagger-ui/**",
            "/**/swagger-resources/**",
            "/login",
            "/join"
    };
}
