package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Kata4Tests {

  Kata4 kata4 = new Kata4();

  //1. If the search text is fewer than 2 characters, then should return no results.
  // (It is an optimization feature of the search functionality.)

  @ParameterizedTest
  @MethodSource("correctCases")
  void call_search(String input, String output) {
    assertEquals(output, kata4.search(input));

  }

  public static Stream<Arguments> correctCases() {
    return Stream.of(
        Arguments.of("", null));

  }


}

