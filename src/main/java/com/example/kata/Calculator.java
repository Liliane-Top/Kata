package com.example.kata;

import com.example.kata.exceptions.InvalidInputForCalculatorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class Calculator {

  List<String> errorMessages = new ArrayList<>();

  public Integer add(String input)
      throws IllegalArgumentException, InvalidInputForCalculatorException {
    var parsed = parse(input);
    var delimiters = parsed.getLeft();
    String numbers = parsed.getRight();

    var validEnding = validateInputEnding(input, numbers);

    var onlyNumbers = retrieveAllNumbersFromInput(delimiters, validEnding);

    var onlyPositiveNumbers = filteringPositiveNumbers(onlyNumbers);

    if (errorMessages.isEmpty()) {
      return onlyPositiveNumbers.reduce(0, Integer::sum);
    } else {
      throw new InvalidInputForCalculatorException(errorMessages);
    }
  }

  private Pair<List<String>, String> parse(String input) {
    var delimiters = List.of(",", "\n");
    if (input.startsWith("//")) {
      delimiters = List.of(input.substring(2).split("\n")[0]);
      input = input.substring(2).split("\n")[1];
    }
    return Pair.of(delimiters, input);
  }

  private String validateInputEnding(String input, String numbers) {
    if (numbers.matches(".*\\D")) {
      errorMessages.add(String.format
          ("Input can't end with '%s'", input.charAt(input.length() - 1)));
      return numbers.replace("\\D", "");
    }
    return numbers;
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
      return filteringPositiveNumbers(Stream.of(Integer.parseInt(str)));
    } catch (NumberFormatException exception) {
      return createErrorMessageForInvalidChar(delim, str, index);
    }

  }

  

  private Stream<Integer> filteringPositiveNumbers(Stream<Integer> numbers) {
    var numbersPartitionedBySign = numbers.collect(
        Collectors.partitioningBy(number -> number > 0));

    var integersNegative = numbersPartitionedBySign.get(false);
    if (integersNegative.isEmpty()) {
      return numbersPartitionedBySign.get(true).stream();
    } else {
      createErrorMessageForNegativeNumbers(integersNegative);
      return Stream.empty();
    }
  }

  private Stream<Integer> createErrorMessageForInvalidChar(List<String> delim, String str, AtomicInteger index) {
    String invalidChar = Arrays.stream(str.split("")).filter(s -> !StringUtils.isNumeric(s))
        .findFirst().orElse("");
    errorMessages.add(String.format("'%s' expected but '%s' found at position %s",
        delim.get(0), invalidChar, index));
    String[] replaceInvalidChar = str.split(Pattern.quote(invalidChar));
    Stream<Integer> numbers = Stream.of(replaceInvalidChar).map(Integer::valueOf);
    return filteringPositiveNumbers(numbers);
  }

  private void createErrorMessageForNegativeNumbers(List<Integer> integersNegative) {
    String negative = integersNegative.get(0).toString();
    errorMessages.add(String.format("Negative number(s) not allowed: %s", negative));
  }
}


