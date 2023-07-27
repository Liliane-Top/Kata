package com.example.kata;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Calculator {

    public Integer add(String input) throws Exception {
        if (StringUtils.isNumeric(input))
            return Integer.parseInt(input);
        if (input.isEmpty())
            return 0;
        if (input.matches(".*[^0-9]"))
            throw new Exception();
        return Arrays.stream(input.split("[,\\n]")).mapToInt(Integer::parseInt).sum();
    }
}
