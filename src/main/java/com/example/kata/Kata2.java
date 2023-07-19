package com.example.kata;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Kata2 {

  private int index = 0;
  private String delimiter = ",";

  public int add(String... numbers) throws IllegalArgumentException {
    return Optional.of(Stream.of(numbers)
            .filter(stream -> !stream.isEmpty())
            .flatMap(checkIfValueEndsWithDelimiter())
            .flatMap(checkIfDifferentDelimiterIsUsed())
            .filter(stream -> !stream.isEmpty())
            .flatMap(checkIfValueIsANumber())
            .flatMapToInt(getSeparatedNumbers())
            .sum())
        .orElse(0);
  }


  private Function<String, Stream<String>> checkIfValueEndsWithDelimiter() {
    return stringNumber -> {
      if (stringNumber.endsWith(delimiter)) {
        throw new IllegalArgumentException();
      } else {
        return Stream.of(stringNumber);
      }
    };
  }


  private Function<String, Stream<String>> checkIfValueIsANumber() {
    return stringNumber -> {
      try {
        ++index;
        Integer result = Integer.parseInt(stringNumber);
        checkIfNumberIsPositive(result);
        return Stream.of(stringNumber);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("expected '" + delimiter +
            "' but found '" + stringNumber + "' found at position " + index);
      }
    };
  }

  private void checkIfNumberIsPositive(Integer stringNumber) {
    if (stringNumber < 0) {
      throw new IllegalArgumentException("Negative number(s) not allowed: " + stringNumber);
    }
  }


  //Todo: this method is doing 2 things
  private Function<String, Stream<String>> checkIfDifferentDelimiterIsUsed() {
    return stringNumber -> {
      if (stringNumber.startsWith("//")) {
        return changeDelimiter(stringNumber);
      } else {
        return Stream.of(stringNumber.split(" *[,\n] *"));
      }
    };
  }

  private Stream<String> changeDelimiter(String stringNumber) {
    String[] splittedString = stringNumber.split("\n");
    delimiter = splittedString[0].replace("//", "");
    return Stream.of(splittedString[1].split(delimiter))
        .map(s -> s.replace(delimiter, ""));
  }


  private Function<String, IntStream> getSeparatedNumbers() {
    return stringNumber -> Stream.of(stringNumber)
        .mapToInt(Integer::valueOf);
  }
}
