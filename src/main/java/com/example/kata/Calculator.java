package com.example.kata;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class Calculator {

  public Integer add(String input) throws IllegalArgumentException {
    var parsed = parse(input);
    var delimiters = parsed.getLeft();
    String numbers = parsed.getRight();

    validateInputEnding(input, numbers);

    var onlyNumbers = retrieveAllNumbersFromInput(delimiters, numbers);

    var onlyPositiveNumbers = filteringPositiveNumbers(onlyNumbers);

    return onlyPositiveNumbers.reduce(0, Integer::sum);
  }

  private Pair<List<String>, String> parse(String input) {
    var delimiters = List.of(",", "\n");
    if (input.startsWith("//")) {
      delimiters = List.of(input.substring(2).split("\n")[0]);
      input = input.substring(2).split("\n")[1];
    }
    return Pair.of(delimiters, input);
  }

  private static void validateInputEnding(String input, String numbers) {
    if (numbers.matches(".*\\D")) {
      throw new IllegalArgumentException(String.format
          ("Input can't end with '%s'", input.charAt(input.length() - 1)));
    }
  }

  private Stream<Integer> retrieveAllNumbersFromInput(List<String> delim, String numbers) {
    var index = new AtomicInteger(1);
    return tokenize(delim, numbers)
        .flatMap(str -> filteringAllNumbers(delim, str, index));
  }

  private Stream<String> tokenize(List<String> delim, String numbers) {
    return Arrays.stream(numbers.split(delim.toString()))
        .filter(s -> !s.isEmpty());
  }

  private Stream<Integer> filteringAllNumbers(List<String> delim, String str,
      AtomicInteger index) {
    try {
      index.getAndIncrement();
      return Stream.of(Integer.parseInt(str));
    } catch (NumberFormatException exception) {
      String invalidChar = Arrays.stream(str.split("")).filter(s -> !StringUtils.isNumeric(s))
          .findFirst().orElse("");
      throw new IllegalArgumentException(
          String.format("'%s' expected but '%s' found at position %s",
              delim.get(0), invalidChar, index));
    }
  }

  private Stream<Integer> filteringPositiveNumbers(Stream<Integer> numbers) {
    var numbersPartitionedBySign = numbers.collect(
        Collectors.partitioningBy(number -> number > 0));

    var integersNegative = numbersPartitionedBySign.get(false);
    if (integersNegative.isEmpty()) {
      return numbersPartitionedBySign.get(true).stream();
    } else {
      throwNoNegativesAllowedException(integersNegative);
      return Stream.empty();
    }
  }

  private void throwNoNegativesAllowedException(List<Integer> integersNegative) {
    String negatives = integersNegative.get(0).toString();
    for (int i = 1; i < integersNegative.size(); i++) {
      negatives = negatives.concat(", " + integersNegative.get(i));
    }
    throw new IllegalArgumentException(
        String.format("Negative number(s) not allowed: %s", negatives));
  }


}
