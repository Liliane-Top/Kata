package com.example.kata;

import java.util.Arrays;
import java.util.Optional;

public record Kata2() {

  public Integer add(String numbers) {
    return Optional.ofNullable(Arrays.stream(numbers.split(","))
        .mapToInt(stringNumber -> Integer.valueOf(stringNumber))
            .sum())
        .orElse(0);
  }

}
