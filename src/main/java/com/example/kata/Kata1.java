package com.example.kata;

import lombok.Builder;

@Builder
public record Kata1(Integer number, String numberAsString) {

  public String convertIntegerToString(Integer number) {
    if (number % 3 == 0 && number % 5 == 0) {
      return "FizzBuzz";
    }
    if (number % 3 == 0) {
      return "Fizz";
    }
    if (number % 5 == 0) {
      return "Buzz";
    }

    return number.toString();

  }

}
