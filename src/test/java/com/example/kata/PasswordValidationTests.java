package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.kata.validators.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PasswordValidationTests {

  private PasswordValidator validation;

  @BeforeEach
  void setUp() {
    validation = PasswordValidator.combine(
            new LengthValidator(),
            new AtLeast2DigitsValidator(),
            new ContainsUppercaseValidator(),
            new ContainsSpecialCharacterValidator());
  }


  @ParameterizedTest
  @MethodSource("correctCases")
  void call_validatePassword(String input) throws IllegalArgumentException {
    validation.validate(input);

  }

  public static Stream<Arguments> correctCases() {
    return Stream.of(
        Arguments.of("0123C45-67"),
        Arguments.of("ab  *c12C 4567"));
  }

  @ParameterizedTest
  @MethodSource("incorrectCases")
  void call_validatePassword_withIncorrectPassword_returnsCorrectMessage(String input,
      String message) {
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> validation.validate(input));
    Assertions.assertEquals(message, exception.getMessage());


  }

  public static Stream<Arguments> incorrectCases() {
    return Stream.of(
        Arguments.of("abx", """
            Password must be at least 8 characters.
            Password must contain at least 2 numbers.
            Password must contain at least one capital letter.
            Password must contain at least one special character."""),
        Arguments.of("abcdE1*fghi", "Password must contain at least 2 numbers."),
        Arguments.of("abc^", """
                Password must be at least 8 characters.
                Password must contain at least 2 numbers.
                Password must contain at least one capital letter."""),
        Arguments.of("abc1234567", """
            Password must contain at least one capital letter.
            Password must contain at least one special character."""),
        Arguments.of("abc12C4567", "Password must contain at least one special character."),
        Arguments.of("abc12C 4567", "Password must contain at least one special character."));
  }

}
