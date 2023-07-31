package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Kata4Tests {

  CitySearch citySearch = new CitySearch();

  //1. If the search text is fewer than 2 characters, then should return no results.
  // (It is an optimization feature of the search functionality.)

  @ParameterizedTest
  @MethodSource("correctCases")
  void call_search(String input, String output) {
    assertEquals(output, citySearch.search(input));

  }

  public static Stream<Arguments> correctCases() {
    return Stream.of(
        Arguments.of("", null),
        Arguments.of("V", null),
        Arguments.of("Amsterdam", "Amsterdam"));

  }

//  @ParameterizedTest
//  @MethodSource("incorrectCases")
//  void call_search_return_null(String input, String output) {
//    assertEquals(output, citySearch.search(input));
//
//  }
//
//  public static Stream<Arguments> incorrectCases() {
//    return Stream.of(
//        Arguments.of("", null));
//
//  }


}

