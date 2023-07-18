package com.example.kata;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Kata2() {

  public int add(String... numbers) throws IllegalArgumentException {

    return Optional.of(Stream.of(numbers)
            .filter(stream -> !stream.isEmpty())
            .flatMap(checkIfValueEndsWithComma())
            .flatMapToInt(getSeparatedNumbers())
            .sum())
        .orElse(0);
  }

  private static Function<String, Stream<String>> checkIfValueEndsWithComma() {
    return stringNumber -> {
      if (stringNumber.endsWith(",")) {
        throw new IllegalArgumentException();
      } else {
        return Stream.of(stringNumber);
      }
    };
  }

  private static Function<String, IntStream> getSeparatedNumbers() {
    return stringNumber -> Arrays.stream(stringNumber.split(" *[,\n] *"))
        .mapToInt(Integer::valueOf);
  }
}
