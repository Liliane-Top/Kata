package com.example.kata;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class Calculator {

  public Integer add(String input) throws IllegalArgumentException {
    Pair<List<String>, String> parsed = parse(input);
    List<String> delim = parsed.getLeft();
    String numbers = parsed.getRight();

    validateInputEnding(input, numbers);

    Stream<Integer> validInput = retrieveAllNumbersFromInput(delim, numbers);

    return filteringPositiveNumbers(validInput)
        .reduce(0, Integer::sum);
  }

  private Pair<List<String>, String> parse(String input) {
    List<String> delim = List.of(",", "\n");
    if (input.startsWith("//")) {
      delim = List.of(input.substring(2).split("\n")[0]);
      input = input.substring(2).split("\n")[1];
    }
    return Pair.of(delim, input);
  }

  private static void validateInputEnding(String input, String numbers) {
    if (numbers.matches(".*\\D")) {
      throw new IllegalArgumentException(String.format
          ("Input can't end with '%s'", input.charAt(input.length() - 1)));
    }
  }

  private Stream<Integer> retrieveAllNumbersFromInput(List<String> delim, String numbers) {
    AtomicInteger index = new AtomicInteger(1);
    return tokenize(delim, numbers)
        .flatMap(str -> filteringNumbers(delim, str, index));
  }

  private Stream<String> tokenize(List<String> delim, String numbers) {
    return Arrays.stream(numbers.split(delim.toString()))
        .filter(s -> !s.isEmpty());
  }

  private Stream<Integer> filteringNumbers(List<String> delim, String str,
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
    Map<Boolean, List<Integer>> numbersDivided = numbers.collect(
        Collectors.partitioningBy(number -> number > 0));

    List<Integer> integersNegative = numbersDivided.get(false);
    if (integersNegative.isEmpty()) {
      return numbersDivided.get(true).stream();
    } else {
      String negatives = integersNegative.get(0).toString();
      for (int i = 1; i < integersNegative.size(); i++) {
        negatives = negatives.concat("," + integersNegative.get(i));
      }
      throw new IllegalArgumentException(
          String.format("Negative number(s) not allowed: %s", negatives));
    }
  }






}
