package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.kata.exceptions.InvalidPasswordException;
import java.util.List;
import java.util.stream.Stream;
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
        Arguments.of("0123C45-67", true)

    );
  }

  @ParameterizedTest
  @MethodSource("incorrectCases")
  void call_validatePassword_withIncorrectPassword(String input, String message,
      List<Integer> errorCodes) {
    InvalidPasswordException exception = new InvalidPasswordException(errorCodes);
    assertThrows(InvalidPasswordException.class, () -> kata3.validatePassword(input));
    assertEquals(message, exception.printMessage());

  }

  public static Stream<Arguments> incorrectCases() {
    return Stream.of(
        Arguments.of("0123", "Password must be at least 8 characters\n", List.of(1)),
        Arguments.of("abcdefghi", "Password must contain at least 2 numbers\n", List.of(2)),
        Arguments.of("abc",
            "Password must be at least 8 characters\nPassword must contain at least 2 numbers\n",
            List.of(1, 2)),
        Arguments.of("abc1234567", "Password must contain at least one capital letter\n",
            List.of(3))

    );
  }

  @Test
  void call_validatePassword_withShortPassword() {
    try {
      kata3.validatePassword("012&34C");
    } catch (InvalidPasswordException ex) {
      assertEquals("Password must be at least 8 characters\n", ex.printMessage());
    }
  }

  @Test
  void call_validatePassword_withLessThanTwoDigits() {
    try {
      kata3.validatePassword("abcd^eCfghj");
    } catch (InvalidPasswordException ex) {
      assertEquals("Password must contain at least 2 numbers\n", ex.printMessage());
    }

  }

  @Test
  void call_validatePassword_wwithLessThanEightNumbersAndLessThanTwoDigits_throwsException() {
    Assertions.assertThrows(InvalidPasswordException.class, () -> kata3.validatePassword("abcC"));
  }

  @Test
  void call_validatePassword_withLessThanEightNumbersAndLessThanTwoDigits_showsCorrectMessage() {
    try {
      kata3.validatePassword("ab!cC");
    } catch (InvalidPasswordException ex) {
      assertEquals(
          "Password must be at least 8 characters\n" + "Password must contain at least 2 numbers\n",
          ex.getMessage());
    }
  }

  @Test
  void call_validatePassword_withoutCapitalLetter_throwsException() {
    Assertions.assertThrows(InvalidPasswordException.class,
        () -> kata3.validatePassword("abc123*4567"));
  }

  @Test
  void call_validatePassword_withoutCapitalLetter_showsCorrectMessage() {
    try {
      kata3.validatePassword("abc1(234567");
    } catch (InvalidPasswordException ex) {
      assertEquals(
          "Password must contain at least one capital letter\n",
          ex.getMessage());
    }
  }

  @Test
  void call_validatePassword_withoutSpecialCharacter_throwsException() {
    Assertions.assertThrows(InvalidPasswordException.class,
        () -> kata3.validatePassword("abc123C4567"));
  }

  @Test
  void call_validatePassword_withoutSpecialCharacter_showsCorrectMessage() {
    try {
      kata3.validatePassword("abc123C4567");
    } catch (InvalidPasswordException ex) {
      assertEquals(
          "Password must contain at least one special character\n",
          ex.getMessage());
    }
  }

}
