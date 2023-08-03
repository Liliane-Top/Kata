package com.example.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

public class Kata2 {


  public int add(String input) {
    List<String> delimiters = new ArrayList<>(Arrays.asList(",", "\n"));

    if (StringUtils.isEmpty(input)) {
      return 0;
    }

    if (input.matches(".*\\D")) {
      throw new NumberFormatException("Input can't end with a delimiter");
    }

    if (input.startsWith("//")) {
      delimiters = List.of(input.substring(2).split("\\n")[0]);
      input = input.split("\\n")[1];
    }

    var numbers = tokenizeInput(input, delimiters);

    var parsedNumbers = parseNumbers(numbers);

    return parsedNumbers.sum();
  }

  private String[] tokenizeInput(String input, List<String> delimiters) {
    return input.split(String.valueOf(delimiters));
  }

  private IntStream parseNumbers(String[] numbers) {
    AtomicInteger index = new AtomicInteger();
    try{
      index.getAndIncrement();
      return Stream.of(numbers).filter(number -> !number.isEmpty()).mapToInt(Integer::parseInt);

    } catch (NumberFormatException exception){
      String message = String.format("'%s' expected but found '%s' at position %s", delimiter, number, index);
      throw new NumberFormatException(message);
    }
  }




}