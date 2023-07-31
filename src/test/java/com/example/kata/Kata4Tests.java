package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Kata4Tests {

  CitySearch citySearch = new CitySearch();

  @ParameterizedTest
  @MethodSource("correctCases")
  void call_search(String input, String output) {
    assertEquals(output, citySearch.search(input));

  }


  public static Stream<Arguments> correctCases() {
    return Stream.of(
        Arguments.of("", ""),
        Arguments.of("V", ""),
        Arguments.of("Amsterdam", "Amsterdam"),
        Arguments.of("Va", "Valencia Vancouver"),
        Arguments.of("amsterdam", "Amsterdam"),
        Arguments.of("AMSTERdam", "Amsterdam"),
        Arguments.of("vA", "Valencia Vancouver"),
        Arguments.of("ape", "Budapest"),
        Arguments.of("Ape", "Budapest"),
        Arguments.of("ME", "Rome"),
        Arguments.of("*", "Paris Budapest Skopje Rotterdam Valencia Vancouver Amsterdam Vienna Sydney New York City London Bangkok Hong Kong Dubai Rome Istanbul"));

  }

}

