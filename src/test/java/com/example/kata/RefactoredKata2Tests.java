package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RefactoredKata2Tests {

  Kata2 kata2 = new Kata2();

  @ParameterizedTest
  @MethodSource("correctCases")
  void call_add_with_correctCases(String input, int output) {
    assertEquals(output, kata2.add(input));

  }

  static Stream<Arguments> correctCases() {
    return Stream.of(
        Arguments.of("", 0),
        Arguments.of("1", 1),
        Arguments.of("234", 234),
        Arguments.of("1,2", 3),
        Arguments.of("1,4,5,6", 16),
        Arguments.of("1,2\n3", 6),
        Arguments.of("//;\n1;3", 4),
        Arguments.of("//;\n1;3;5", 9),
        Arguments.of("//|\n1|2|3", 6),
        Arguments.of("//sep\n2sep5", 7)
    );

  }

  @ParameterizedTest
  @MethodSource("incorrectCases")
  void call_add_with_incorrectCases(String input, String message) {
    Exception exception = assertThrows(NumberFormatException.class, () -> kata2.add(input));
    assertEquals(message, exception.getMessage());

  }

  static Stream<Arguments> incorrectCases() {
    return Stream.of(
        Arguments.of("1,2,", "Input can't end with a delimiter"),
        Arguments.of("1,2,3\n", "Input can't end with a delimiter")
    );
  }

}
