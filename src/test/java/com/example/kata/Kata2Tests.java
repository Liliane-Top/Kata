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





  //4. Add validation to not to allow a separator at the end
  //
  //For example “1,2,” should return an error (or throw an exception)

  @Test
  void call_addWithArgumentsEndingWithDelimiter_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> kata2.add("1,2,"));
  }

  //5. Allow the add method to handle different delimiters
  //
  //To change the delimiter, the beginning of the input will contain a separate line
  // that looks like this:
  ////[delimiter]\n[numbers]
  //“//;\n1;3” should return “4”
  //“//|\n1|2|3” should return “6”
  //“//sep\n2sep5” should return “7”


  @Test
  void call_addWithDifferentDelimiters_returnSumOfArgument() {
    assertEquals(4, kata2.add("//;\n1;3"));
    assertEquals(9, kata2.add("//;\n1;3;5"));
    assertEquals(6, kata2.add("//|\n1|2|3"));
    assertEquals(7, kata2.add("//sep\n2sep5"));
  }


  @Test
  void call_addWithDifferentDelimitersAndInvalidString_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> kata2.add("//|\n1|2,3"));
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
