package com.example.kata;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

public class Kata2 {

  private static int index;
  public static String delimiter = ",";

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

  private static Function<String, Stream<String>> checkIfValueEndsWithDelimiter() {
    return stringNumber -> {
      if (stringNumber.endsWith(delimiter)) {
        throw new IllegalArgumentException();
      } else {
        return Stream.of(stringNumber);
      }
    };
  }

  private static Function<String, Stream<String>>  checkIfValueIsANumber() {
    return stringNumber -> {
      ++index;
      if (StringUtils.isNumeric(stringNumber)) {
        return Stream.of(stringNumber);
      } else {
        throw new NumberFormatException("expected '" + delimiter +
            "' but found '" + stringNumber + "' found at position " + index);
      }
    };

  }

  //Todo: this method is doing 2 things
  private static Function<String, Stream<String>> checkIfDifferentDelimiterIsUsed() {
    return stringNumber -> {
      if (stringNumber.startsWith("//")) {
        return changeDelimiter(stringNumber);
      } else {
        return Stream.of(stringNumber.split(" *[,\n] *"));
      }
    };
  }

  private static Stream<String> changeDelimiter(String stringNumber) {
    String[] splittedString = stringNumber.split("\n");
    delimiter = splittedString[0].replace("//", "");
    return Stream.of(splittedString[1].split(delimiter))
        .map(s -> s.replace(delimiter, ""));
  }


  private static Function<String, IntStream> getSeparatedNumbers() {
    return stringNumber -> Stream.of(stringNumber)
        .mapToInt(Integer::valueOf);
  }
}
