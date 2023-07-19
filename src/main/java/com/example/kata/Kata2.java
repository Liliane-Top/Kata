package com.example.kata;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Kata2() {

  // “//|\n1|2,3” is invalid and should return an error (or throw an exception)
  //  with the message “‘|’ expected but ‘,’ found at position 3.”

  public int add(String... numbers) throws IllegalArgumentException {
    return Optional.of(Stream.of(numbers)
            .filter(stream -> !stream.isEmpty())
            .flatMap(checkIfValueEndsWithDelimiter())
            .flatMap(checkIfDifferentDelimiterIsUsed())
                .filter( s -> !s.isEmpty())
            .peek(System.out::println)
            .flatMapToInt(getSeparatedNumbers())
            .sum())
        .orElse(0);
  }
// “//;\n1;3” should return “4”
//  “//|\n1|2|3” should return “6”
//  “//sep\n2sep5” should return “7”
//  “//|\n1|2,3”
  private static Function<String, Stream<String>> checkIfDifferentDelimiterIsUsed() {
    return stringNumber -> {
      if (stringNumber.startsWith("//")) {
        String[] splittedString = stringNumber.split("\n");
        String delimiter = splittedString[0].replace("//", "");
        return Stream.of(splittedString[1].split(delimiter))
            .map(s -> s.replace(delimiter, ""));
      } else {
      return Stream.of(stringNumber.split(" *[,\n] *"));
    }};
  }



  private static Function<String, Stream<String>> checkIfValueEndsWithDelimiter() {
    return stringNumber -> {
      if (stringNumber.endsWith(",")) {
        throw new IllegalArgumentException();
      } else {
        return Stream.of(stringNumber);
      }
    };
  }

  private static Function<String, IntStream> getSeparatedNumbers() {
    return stringNumber -> Stream.of(stringNumber)
        .mapToInt(Integer::valueOf);
  }
}
