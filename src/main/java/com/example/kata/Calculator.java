package com.example.kata;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class Calculator {

  public Integer add(String input) throws IllegalArgumentException {

    Pair<List<String>, String> parsed = parse(input);

    List<String> delim = parsed.getLeft();
    String numbers = parsed.getRight();

    if (StringUtils.isNumeric(numbers)) {
      return Integer.parseInt(numbers);
    }
    if (numbers.isEmpty()) {
      return 0;
    }
    if (numbers.matches(".*\\D")) {
      throw new IllegalArgumentException(String.format
          ("Input can't end with '%s'", input.charAt(input.length() - 1)));
    }
    return tokenize(delim, numbers).sum();
  }

  private IntStream tokenize(List<String> delim, String numbers) {
    AtomicInteger index = new AtomicInteger(1);
    return Arrays.stream(numbers.split(delim.toString()))
        .filter(s -> !s.isEmpty())
        .flatMap(str -> checkIfOnlyPositiveNumbers(delim, index, str))
        .mapToInt(Integer::parseInt);
  }

  private Stream<String> checkIfOnlyPositiveNumbers(List<String> delim, AtomicInteger index,
      String str) {
    try {
      index.getAndIncrement();
      Integer number = Integer.parseInt(str);
      checkIfNumberIsPositive(number);
      return Stream.of(str);
    } catch (NumberFormatException exception) {
      String invalidChar = Arrays.stream(str.split("")).filter(s -> !StringUtils.isNumeric(s))
          .findFirst().orElse("");
      throw new IllegalArgumentException(String.format("'%s' expected but '%s' found at position %s",
          delim.get(0), invalidChar, index));
    }
  }
  private void checkIfNumberIsPositive(Integer stringNumber) {
      if (stringNumber < 0) {
        throw new IllegalArgumentException(String.format("Negative number(s) not allowed: %s", stringNumber));
      }
    }

  private Pair<List<String>, String> parse(String input) {
    List<String> delim = List.of(",", "\n");
    if (input.startsWith("//")) {
      delim = List.of(input.substring(2).split("\n")[0]);
      input = input.substring(2).split("\n")[1];
    }
    return Pair.of(delim, input);
  }
}
