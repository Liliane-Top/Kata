package com.example.kata;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@AllArgsConstructor
@Component
public class Calculator {

    private final Parser parser;
    private final Tokenizer tokenizer;


    public int add(String input) {

        ErrorBuilder errors = new ErrorBuilder();

        Tokenizer.Tokenized tokenized = tokenizer.tokenize(input, errors);

        int[] parsed = parser.parse(tokenized, errors);

        if (errors.hasError())
            throw errors.getException();

        return Arrays.stream(parsed)
                .sum();
    }

}
