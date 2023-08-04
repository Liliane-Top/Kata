package com.example.kata;

import static com.example.kata.ErrorMessage.createErrorMessage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

public class Validator {

  public static String validateInputEnding(String input) {
    return input.matches(".*\\D") ? removeDelimiterAtEnd(input) : input;
  }

  private static String removeDelimiterAtEnd(String input) {
    createErrorMessage("Separator at the end is not allowed.");
    return input.substring(0, input.length() - 1);
  }


  public static boolean validateChangedDelimiter(String input) {
    return input.startsWith("//");
  }

  public static Function<String, Stream<Integer>> checkIfValuesIsANumber(List<String> delimiter) {
    var index = new AtomicInteger(1);
    return stringNumber -> {
      try {
        index.getAndIncrement();
        return Stream.of(Integer.parseInt(stringNumber));
      } catch (NumberFormatException exception) {
        return replaceInvalidDelimiter(delimiter.get(0), stringNumber, index);
      }
    };
  }

  private static Stream<Integer> replaceInvalidDelimiter(String delimiter,
      String stringNumber, AtomicInteger index) {
    String invalidDelimiter = Arrays.stream(stringNumber.split(""))
        .filter(s -> !StringUtils.isNumeric(s))
        .findFirst()
        .orElse("");

    createErrorMessage(String.format("'%s' expected but '%s' found at position %s.",
        delimiter, invalidDelimiter, index));

    return Stream.of(stringNumber.split(Pattern.quote(invalidDelimiter))).map(Integer::parseInt);

  }
}

