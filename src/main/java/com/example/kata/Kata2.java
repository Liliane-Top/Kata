package com.example.kata;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public record Kata2() {

  public int add(String... numbers) {
    return Optional.of(Stream.of(numbers)
            .filter(stream -> !stream.isEmpty())
            .flatMapToInt(stringNumber -> Arrays.stream(stringNumber.split("\s*[,\n]\s*"))
                .mapToInt(Integer::valueOf))
            .sum())
        .orElse(0);
  }
}
