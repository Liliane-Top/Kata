package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.kata.exceptions.InvalidPasswordException;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Kata3Tests {

  private Kata3 kata3;

  @BeforeEach
  void setUp() {
    kata3 = new Kata3();
  }


  @ParameterizedTest
  @MethodSource("correctCases")
  void call_validatePassword(String input, Boolean output) throws InvalidPasswordException {
    assertEquals(output, kata3.validatePassword(input));

  }

  public static Stream<Arguments> correctCases() {
    return Stream.of(
        Arguments.of("0123C45-67", true),
        Arguments.of("ab  *c12C 4567", true));
  }

  @ParameterizedTest
  @MethodSource("incorrectCases")
  void call_validatePassword_withIncorrectPassword_returnsCorrectMessage(String input,
      String message)
      throws InvalidPasswordException {
    Exception exception = assertThrows(InvalidPasswordException.class,
        () -> kata3.validatePassword(input));
    Assertions.assertEquals(message, exception.getMessage());


  }

  public static Stream<Arguments> incorrectCases() {
    return Stream.of(
        Arguments.of("abx",
            "Password must be at least 8 characters\n"
                + "Password must contain at least 2 numbers\n"
                + "Password must contain at least one capital letter\n"
                + "Password must contain at least one special character\n"),
        Arguments.of("abcdE1*fghi", "Password must contain at least 2 numbers\n"),
        Arguments.of("abc^",
            "Password must be at least 8 characters\n"
                + "Password must contain at least 2 numbers\n"
                + "Password must contain at least one capital letter\n"),
        Arguments.of("abc1234567", "Password must contain at least one capital letter\n"
            + "Password must contain at least one special character\n"),
        Arguments.of("abc12C4567", "Password must contain at least one special character\n"),
        Arguments.of("abc12C 4567", "Password must contain at least one special character\n"));
  }

  @Test
  void call_validatePassword_withLessThanEightCharacters_throwsException_showsCorrectMessage() {
    Exception exception = assertThrows(InvalidPasswordException.class,
        () -> kata3.validatePassword("a*cC12"));
    assertEquals("Password must be at least 8 characters\n", exception.getMessage());
  }

  @Test
  void call_validatePassword_withLessThanTwoDigits_throwsException_showsCorrectMessage() {
    Exception exception = assertThrows(InvalidPasswordException.class,
        () -> kata3.validatePassword("abcd^eCfghj"));
    assertEquals("Password must contain at least 2 numbers\n", exception.getMessage());
  }

  @Test
  void call_validatePassword_withLessThanEightNumbersAndLessThanTwoDigits_throwsException_showsCorrectMessage() {
    Exception exception = assertThrows(InvalidPasswordException.class, () ->
        kata3.validatePassword("ab!cC1"));
    assertEquals(
        "Password must be at least 8 characters\n" + "Password must contain at least 2 numbers\n",
        exception.getMessage());
  }

  @Test
  void call_validatePassword_withoutCapitalLetter_throwsException_showsCorrectMessage() {
    Exception exception = assertThrows(InvalidPasswordException.class,
        () -> kata3.validatePassword("abc123|4567"));
    assertEquals(
        "Password must contain at least one capital letter\n",
        exception.getMessage());
  }

  @Test
  void call_validatePassword_withoutSpecialCharacter_throwsException_showsCorrectMessage() {
    Exception exception = assertThrows(InvalidPasswordException.class,
        () -> kata3.validatePassword("abc123C4567"));
    assertEquals(
        "Password must contain at least one special character\n",
        exception.getMessage());
  }
}
