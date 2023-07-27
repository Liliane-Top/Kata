package com.example.kata;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class Calculator {

    public Integer add(String input) throws Exception {

        Pair<String, String> parsed = parse(input);

        String delim = parsed.getLeft();
        String numbers = parsed.getRight();

        if (StringUtils.isNumeric(numbers))
            return Integer.parseInt(numbers);
        if (numbers.isEmpty())
            return 0;
        if (numbers.matches(".*[^0-9]"))
            throw new Exception();
        return Arrays.stream(numbers.split(delim)).mapToInt(Integer::parseInt).sum();
    }

    private Pair<String, String> parse(String input) {
        String delim = "[,\\n]";
        if (input.startsWith("//"))
        {
            delim = input.substring(2).split("\n")[0];
            input = input.substring(2).split("\n")[1];
        }
        return Pair.of(delim, input);
    }
}
