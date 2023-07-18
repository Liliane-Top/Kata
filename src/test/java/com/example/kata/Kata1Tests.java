package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//Notes:
//
//start with the minimal failing solution
//keep the three rules in mind and always write just sufficient enough code
//1- You are not allowed to write any production code unless it is to make a failing unit test pass.
//2- You are not allowed to write any more of a unit test than is sufficient to fail, and compilation failures are failures.
//3- You are not allowed to write any more production code than is sufficient to pass the one failing unit test.
//do not forget to refactor your code after each passing test
//write your assertions relating to the exact requirements

class Kata1Tests {

  private static Kata1 kata1;
  @BeforeAll
  static void setUp() {
    kata1 = new Kata1(null, null);
  }

//1. Write a “fizzBuzz” method that accepts a number as input and returns it as a String.
  @Test
  void call_convertIntegerToStringWithNumber_returnAsString() {
    assertEquals("1966", kata1.convertIntegerToString(1966));
  }

  //2. For multiples of three return “Fizz” instead of the number
  @Test
  void call_convertIntegerToStringWithMultiplesOfThree_returnFizz() {
    assertEquals("Fizz", kata1.convertIntegerToString(369));
  }

  //3. For the multiples of five return “Buzz”
  @Test
  void call_convertIntegerToStringWithMultiplesOfFive_returnBuzz() {
    assertEquals("Buzz", kata1.convertIntegerToString(625));
  }

  //4. For numbers that are multiples of both three and five return “FizzBuzz”.
  @Test
  void call_convertIntegerToStringWithMultiplesOfThreeAndMultiplesOfFive_returnFizzBuzz() {
    assertEquals("FizzBuzz", kata1.convertIntegerToString(15));
  }


}
