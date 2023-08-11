package com.example.kata;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
class Parser {
    public int[] parse(Tokenizer.Tokenized tokenized, ErrorBuilder errors) {
        int[] res = Arrays.stream(tokenized.input().split(String.valueOf(tokenized.delimiters())))
                .filter(str -> !str.isEmpty())
                .flatMap(checkIfValuesIsANumber(tokenized.delimiters(), errors))
                .mapToInt(Integer::intValue)
                .toArray();
        return res;
    }


    private Function<String, Stream<Integer>> checkIfValuesIsANumber(List<String> delimiter, ErrorBuilder errors) {
        var index = new AtomicInteger(1);
        return stringNumber -> {
            try {
                index.getAndIncrement();
                return Stream.of(Integer.parseInt(stringNumber));
            } catch (NumberFormatException exception) {
                String invalidDelimiter = Arrays.stream(stringNumber.split(""))
                        .filter(s -> !StringUtils.isNumeric(s))
                        .findFirst()
                        .orElse("");

                errors.addErrorMessage(String.format("'%s' expected but '%s' found at position %s.", delimiter.get(0), invalidDelimiter, index));

                return Stream.of(stringNumber.split(Pattern.quote(invalidDelimiter))).map(Integer::parseInt);

            }
        };
    }
}
