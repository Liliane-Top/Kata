package com.example.kata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//Kata 2 – String calculator
//Create a simple calculator that takes a String and returns a integer
//
//Signature (pseudo code):
//
//int Add(string numbers)
//Requirements
//1. The method can take up to two numbers, separated by commas,
// and will return their sum as a result.
// So the inputs can be: “”, “1”, “1,2”. For an empty string, it will return 0.
//
//Notes:
//
//start with the simplest case (empty string) and extend it with the more advanced cases (“1” and “1,2”) step by step
//keep the three rules in mind and always write just sufficient enough code
//do not forget to refactor your code after each passing test
class Kata2Tests {

  private static Kata2 kata2;

  @BeforeAll
  static void setUp() {
    kata2 = new Kata2();
  }

  @Test
  void call_addWithEmptyString_returnInteger0() {
    assertEquals(0, kata2.add(""));
  }

  @Test
  void call_addWithStringValue1_returnInteger1() {
    assertEquals(1, kata2.add("1"));
  }

  @Test
  void call_addWithStringValue1comma2_returnInteger12() {
    assertEquals(3, kata2.add("1,2"));
  }

  //2. Allow the add method to handle an unknown number of arguments
  @Test
  void call_addWithUnknownNumberOfArguments_returnSumOfArguments(){
    assertEquals(13, kata2.add("1,2,3", "3,4", ""));
  }

  //3. Allow the add method to handle newlines as separators, instead of comas
  //
  //“1,2\n3” should return “6”
  //“2,\n3” is invalid, but no need to clarify it with the program

  @Test
  void call_addWithArgumentsSeperatedWithNewlines_returnSumOfArguments(){
    assertEquals(6, kata2.add("1,2\n3"));
  }

  //4. Add validation to not to allow a separator at the end
  //
  //For example “1,2,” should return an error (or throw an exception)

  @Test
  void call_addWithArgumentsEndingWithComma_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      kata2.add("1,2,");
    });
  }

  //5. Allow the add method to handle different delimiters
  //
  //To change the delimiter, the beginning of the input will contain a separate line
  // that looks like this:
  ////[delimiter]\n[numbers]
  //“//;\n1;3” should return “4”
  //“//|\n1|2|3” should return “6”
  //“//sep\n2sep5” should return “7”
  //“//|\n1|2,3” is invalid and should return an error (or throw an exception)
  // with the message “‘|’ expected but ‘,’ found at position 3.”

  @Test
  void call_addWithDifferentDelimiters_returnSumOfArgument() {
    assertEquals(4, kata2.add("//;\n1;3"));
    assertEquals(6, kata2.add("//|\n1|2|3"));
    assertEquals(7, kata2.add("//sep\n2sep5"));
  }

}
