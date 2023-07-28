package com.example.kata;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class Calculator {


  public Integer add(String input) throws IllegalArgumentException {
    Pair<List<String>, String> parsed = parse(input);
    List<String> delim = parsed.getLeft();
    String numbers = parsed.getRight();
    AtomicInteger index = new AtomicInteger(1);

    if (numbers.matches(".*\\D")) {
      throw new IllegalArgumentException(String.format
          ("Input can't end with '%s'", input.charAt(input.length() - 1)));
    }

    Stream<Integer> validInput = tokenize(delim, numbers)
        .flatMap(str -> checkIfValueIsANumber(delim, str, index));

    List<Integer> negativeNumbers = validInput.flatMap(number -> checkIfNumberIsNegative(number))
        .toList();


    if (!negativeNumbers.isEmpty()) {
      String negatives = negativeNumbers.get(0).toString();
      for (int i = 1; i < negativeNumbers.size(); i++) {
        negatives = negatives.concat( "," + negativeNumbers.get(i));
      }
      throw new IllegalArgumentException(
          String.format("Negative number(s) not allowed: %s", negatives));
    }
    Stream<Integer> validInput2 = tokenize(delim, numbers)
        .flatMap(str -> checkIfValueIsANumber(delim, str, index));


    return validInput2.reduce(0, Integer::sum);
  }

  private Stream<String> tokenize(List<String> delim, String numbers) {
    return Arrays.stream(numbers.split(delim.toString()))
        .filter(s -> !s.isEmpty());
  }


  private Stream<Integer> checkIfValueIsANumber(List<String> delim, String str,
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

  private Stream<Integer> checkIfNumberIsNegative(Integer number) {
    if (number < 0) {
      return Stream.of(number);
    } else {
      return Stream.empty();
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
