package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  void call_validatePassword(String input, Integer output)  {
    assertEquals(output, kata3.add(input));

  }

  public static Stream<Arguments> correctCases() {
    return Stream.of(
        Arguments.of("", 0 ));
  }

  //1. The method can take up to two numbers, separated by commas, and will return their sum as a result. So the inputs can be: “”, “1”, “1,2”. For an empty string, it will return 0.
  //
  //Notes:
  //
  //start with the simplest case (empty string) and extend it with the more advanced cases (“1” and “1,2”) step by step
  //keep the three rules in mind and always write just sufficient enough code
  //do not forget to refactor your code after each passing test



}
