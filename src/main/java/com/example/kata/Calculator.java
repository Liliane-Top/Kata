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

  private final List<String> errorMessages = new ArrayList<>();

  public Integer add(String input) throws  InvalidInputForCalculatorException {
    var parsed = parse(input);
    var delimiters = parsed.getLeft();
    String numbers = parsed.getRight();

    var validEnding = validateInputEnding(input, numbers);

    var onlyNumbers = retrieveAllNumbersFromInput(delimiters, validEnding);

    var onlyPositiveNumbers = filteringPositiveNumbers(onlyNumbers);

    var onlyNumbersBelow1000 = ignoreNumbersAbove1000(onlyPositiveNumbers);

    if (errorMessages.isEmpty()) {
      return onlyNumbersBelow1000.reduce(0, Integer::sum);
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
    return numbers.matches(".*\\D") ? replaceInvalidEnding(input, numbers) : numbers;
  }

  private String replaceInvalidEnding(String input, String numbers) {
    createErrorMessageForInvalidEnding(input);
    return numbers.replace("\\D", "");
  }

  private Stream<Integer> retrieveAllNumbersFromInput(List<String> delim, String numbers) {
    var index = new AtomicInteger(1);
    return Arrays.stream(numbers.split(delim.toString()))
        .filter(s -> !s.isEmpty())
        .flatMap(str -> filteringAllNumbers(delim, str, index));
  }

  private Stream<Integer> filteringAllNumbers(List<String> delim, String str,
      AtomicInteger index) {
    try {
      index.getAndIncrement();
      return filteringPositiveNumbers(Stream.of(Integer.parseInt(str)));
    } catch (NumberFormatException exception) {
      return replaceInvalidDelimiter(delim, str, index);
    }
  }

  private Stream<Integer> filteringPositiveNumbers(Stream<Integer> numbers) {
    var partitionedBySign = numbers.collect(Collectors.partitioningBy(number -> number > 0));
    var negatives = partitionedBySign.get(false);

    return negatives.isEmpty() ? partitionedBySign.get(true).stream() :
        createErrorMessageForNegativeNumbers(negatives);
  }

  private Stream<Integer> ignoreNumbersAbove1000(Stream<Integer> onlyPositiveNumbers) {
    return onlyPositiveNumbers.map(number -> (number > 1000? 0 : number));
  }

  private Stream<Integer> replaceInvalidDelimiter(List<String> delim, String str,
      AtomicInteger index) {
    String invalidDelimiter = Arrays.stream(str.split("")).filter(s -> !StringUtils.isNumeric(s))
        .findFirst().orElse("");
    createErrorMessageForInvalidDelimiter(delim, index, invalidDelimiter);

    String[] replaceInvalidChar = str.split(Pattern.quote(invalidDelimiter));
    Stream<Integer> numbers = Stream.of(replaceInvalidChar).map(Integer::valueOf);
    return filteringPositiveNumbers(numbers);
  }

  private void createErrorMessageForInvalidEnding(String input) {
    errorMessages.add(String.format
        ("Input can't end with '%s'", input.charAt(input.length() - 1)));
  }

  private void createErrorMessageForInvalidDelimiter(List<String> delim, AtomicInteger index, String invalidDelimiter) {
    errorMessages.add(String.format("'%s' expected but '%s' found at position %s",
        delim.get(0), invalidDelimiter, index));
  }

  private Stream<Integer> createErrorMessageForNegativeNumbers(List<Integer> negatives) {
    errorMessages.add(String.format("Negative number(s) not allowed: %s", negatives.get(0).toString()));
    return Stream.empty();
  }
}


