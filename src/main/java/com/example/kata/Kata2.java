package com.example.kata;

import java.util.Arrays;
import java.util.Optional;

public record Kata2() {

  public int add(String numbers) {
    return Optional.of(Arrays.stream(numbers.split(","))
            .filter(stream -> !stream.isEmpty())
            .mapToInt(Integer::valueOf)
            .sum())
        .orElse(0);
  }

}
