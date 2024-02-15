package com.bg.bzahov.achievementsBG.constants;

import org.intellij.lang.annotations.RegExp;

public class RegexPatterns {
    @RegExp
    // 5-20 characters containing only letters, numbers and special characters
    public static final String PATTERN_PASSWORD = "^[A-Za-z\\d@$!%*#?&]{5,20}$";
    @RegExp
    // 5-20 characters containing only letters, numbers and underscores
    public static final String PATTERN_USERNAME = "^(\\w){5,20}";

}
