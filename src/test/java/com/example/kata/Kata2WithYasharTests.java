package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Kata2WithYasharTests {

  private Calculator kata3;

  @BeforeEach
  void setUp() {
    kata3 = new Calculator();
  }


  @ParameterizedTest
  @MethodSource("correctCases")
  void call_add(String input, Integer output) throws Exception {
    assertEquals(output, kata3.add(input));

  }

  public static Stream<Arguments> correctCases() {
    return Stream.of(
        Arguments.of("", 0),
        Arguments.of("1", 1),
        Arguments.of("1,2", 3),
        Arguments.of("2", 2),
        Arguments.of("435", 435),
        Arguments.of("435,5", 440),
        Arguments.of("435,5,6", 446),
        Arguments.of("1,2\n3", 6),
        Arguments.of("//;\n1;3", 4),
        Arguments.of("//;\n2;3", 5),
        Arguments.of("//|\n1|2|3", 6),
        Arguments.of("//sep\n2sep5", 7));
  }


  @ParameterizedTest
  @MethodSource("incorrectCases")
  void call_add(String input, String message) {
    Exception exception = assertThrows(Exception.class,
        () -> kata3.add(input));

    assertEquals(message, exception.getMessage());

  }

  public static Stream<Arguments> incorrectCases() {
    return Stream.of(
        Arguments.of("1,2,", "Input can't end with ','"),
        Arguments.of("1,2,\n", "Input can't end with '\n'"),
        Arguments.of("//|\n1|2,3", "'|' expected but ',' found at position 3"),
        Arguments.of("//res\n1res2res3|6", "'res' expected but '|' found at position 4"),
        Arguments.of("1,-2", "Negative number(s) not allowed: -2"),
        Arguments.of("2,-4,-9", "Negative number(s) not allowed: -4, -9"));
  }
}
