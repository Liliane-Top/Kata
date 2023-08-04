package com.example.kata;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Calculator {

  public int add(String input) {
    List<String> delimiters = List.of(",", "\n");
    return Optional.of(Arrays.stream(input.split(String.valueOf(delimiters)))
            .filter(str -> !str.isEmpty())
            .mapToInt(Integer::parseInt)
            .sum())
        .orElse(0);
  }

}