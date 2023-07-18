package com.example.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//1. Write a “fizzBuzz” method that accepts a number as input and returns it as a String.
//
//Notes:
//
//start with the minimal failing solution
//keep the three rules in mind and always write just sufficient enough code
//do not forget to refactor your code after each passing test
//write your assertions relating to the exact requirements
public class Kata1Tests {

  @Test
  void call_convertIntegerToStringWithNumber_returnAsString(){
    Kata1 kata1 = new Kata1(null, null);
    Assertions.assertEquals("1966", kata1.convertIntegerToString(1966));
  }

}
