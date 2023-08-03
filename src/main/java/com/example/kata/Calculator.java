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


  public Integer add(String input) throws  InvalidInputForCalculatorException {
    List<String> errorMessages = new ArrayList<>();
    var parsed = parse(input);
    var delimiters = parsed.getLeft();
    String numbers = parsed.getRight();

    var validEnding = validateInputEnding(input, numbers, errorMessages);

    var onlyNumbers = retrieveAllNumbersFromInput(delimiters, validEnding, errorMessages);

    var onlyPositiveNumbers = filteringPositiveNumbers(onlyNumbers, errorMessages);

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

  private String validateInputEnding(String input, String numbers, List<String> errorMessages) {
    return numbers.matches(".*\\D") ? replaceInvalidEnding(input, numbers, errorMessages) : numbers;
  }

  private String replaceInvalidEnding(String input, String numbers, List<String> errorMessages) {
    createErrorMessageForInvalidEnding(input, errorMessages);
    return numbers.replace("\\D", "");
  }

  private Stream<Integer> retrieveAllNumbersFromInput(List<String> delim, String numbers, List<String> errorMessages) {
    var index = new AtomicInteger(1);
    return Arrays.stream(numbers.split(delim.toString()))
        .filter(s -> !s.isEmpty())
        .flatMap(str -> filteringAllNumbers(delim, str, index, errorMessages));
  }

  private Stream<Integer> filteringAllNumbers(List<String> delim, String str,
      AtomicInteger index, List<String> errorMessages) {
    try {
      index.getAndIncrement();
      return filteringPositiveNumbers(Stream.of(Integer.parseInt(str)),errorMessages);
    } catch (NumberFormatException exception) {
      return replaceInvalidDelimiter(delim, str, index, errorMessages);
    }
  }

  private Stream<Integer> filteringPositiveNumbers(Stream<Integer> numbers, List<String> errorMessages) {
    var partitionedBySign = numbers.collect(Collectors.partitioningBy(number -> number > 0));
    var negatives = partitionedBySign.get(false);

    return negatives.isEmpty() ? partitionedBySign.get(true).stream() :
        createErrorMessageForNegativeNumbers(negatives, errorMessages);
  }

  private Stream<Integer> ignoreNumbersAbove1000(Stream<Integer> onlyPositiveNumbers) {
    return onlyPositiveNumbers.map(number -> (number > 1000? 0 : number));
  }

  private Stream<Integer> replaceInvalidDelimiter(List<String> delim, String str,
      AtomicInteger index, List<String> errorMessages) {
    String invalidDelimiter = Arrays.stream(str.split("")).filter(s -> !StringUtils.isNumeric(s))
        .findFirst().orElse("");
    createErrorMessageForInvalidDelimiter(delim, index, invalidDelimiter, errorMessages);

    String[] replaceInvalidChar = str.split(Pattern.quote(invalidDelimiter));
    Stream<Integer> numbers = Stream.of(replaceInvalidChar).map(Integer::valueOf);
    return filteringPositiveNumbers(numbers, errorMessages);
  }

  private void createErrorMessageForInvalidEnding(String input, List<String> errorMessages) {
    errorMessages.add(String.format
        ("Input can't end with '%s'", input.charAt(input.length() - 1)));
  }

  private void createErrorMessageForInvalidDelimiter(List<String> delim, AtomicInteger index,
      String invalidDelimiter, List<String> errorMessages) {
    errorMessages.add(String.format("'%s' expected but '%s' found at position %s",
        delim.get(0), invalidDelimiter, index));
  }

  private Stream<Integer> createErrorMessageForNegativeNumbers(List<Integer> negatives, List<String> errorMessages) {
    errorMessages.add(String.format("Negative number(s) not allowed: %s", negatives.get(0).toString()));
    return Stream.empty();
  }
}


