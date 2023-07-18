package com.example.kata;

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
    Assertions.assertEquals(0, kata2.add(""));
  }

  @Test
  void call_addWithStringValue1_returnInteger1() {
    Assertions.assertEquals(1, kata2.add("1"));
  }

  @Test
  void call_addWithStringValue1comma2_returnInteger12() {
    Assertions.assertEquals(3, kata2.add("1,2"));
  }

  //2. Allow the add method to handle an unknown number of arguments

  @Test
  void call_addWithUnknownNumberOfArguments_returnSumOfArguments(){
    Assertions.assertEquals(13, kata2.add("1,2,3", "3,4", ""));
  }
}
