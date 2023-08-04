package com.example.kata;

import static com.example.kata.ErrorMessage.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntUnaryOperator;

public class Calculator {

  public int add(String input) {
    List<String> delimiters = List.of(",", "\n");

    input = Validator.validateInputEnding(input);

    return Optional.of(Arrays.stream(input.split(String.valueOf(delimiters)))
            .filter(str -> !str.isEmpty())
            .mapToInt(Integer::parseInt)
            .map(checkErrors())
            .sum())
        .orElse(0);
  }

  private static IntUnaryOperator checkErrors() {
    return number -> {
      if (!errorMessages.isEmpty()) {
        throw new NumberFormatException(errorMessages.toString());
      }
      return number;
    };
  }

}