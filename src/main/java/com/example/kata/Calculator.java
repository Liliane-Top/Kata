package com.example.kata;

import org.apache.commons.lang3.StringUtils;

public class Calculator {

    public Integer add(String input) {
        if (StringUtils.isNumeric(input))
            return Integer.parseInt(input);
        return input.length();
    }
}
