package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Kata2Tests {

  private Kata2 kata2;

  @BeforeEach
  void setUp() {
    kata2 = new Kata2();
  }









  @Test
  void call_addWithDifferentDelimitersAndInvalidString_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> kata2.add());
  }


  //“//|\n1|2,3” is invalid and should return an error (or throw an exception)
  // with the message “‘|’ expected but ‘,’ found at position 3.”
  @Test
  void call_addWithDifferentDelimitersAndInvalidString_throwsException_returnsCorrectMessage() {
    try {
      kata2.add("//|\n1|2,3");
    } catch (IllegalArgumentException e) {
      assertEquals("'|' expected but ',' found at position 3", e.getMessage());
    }
  }

  //6. Calling add with negative numbers will return the message “Negative number(s) not allowed: <negativeNumbers>”
  //
  //“1,-2” is invalid and should return the message “Negative number(s) not allowed: -2”
  //“2,-4,-9” is invalid and should return the message “Negative number(s) not allowed: -4, -9”
  @Test
  void call_addWithNegativeNumbers_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> kata2.add("//|\n1|2,3"));
  }

  @Test
  void call_addWithNegativeNumbers_throwsException_returnsCorrectMessage() {
    try {
      kata2.add("1,-2");
    } catch (IllegalArgumentException e) {
      assertEquals("Negative number(s) not allowed: -2", e.getMessage());
    }
  }

  //7. Calling add with multiple errors will return all error messages separated by newlines.
  // “//|\n1|2,-3” is invalid and return the message “Negative number(s) not allowed: -3\n’|’
  // expected but ‘,’ found at position 3.”

//  @Test
//  void call_addWithMultipleErrors_showAllMessagesOnSeperateLines() {
//    try {
//      kata2.add("//|\n1|2,-3");
//      } catch(IllegalArgumentException e){
//      assertEquals("Negative number(s) not allowed: -3\n '|' expected but ‘,’ found at position 3", e.getMessage());
//
//    }
//  }
}
