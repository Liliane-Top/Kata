package com.example.kata;

public record Kata2() {

  public Integer add(String numbers) {
    if(numbers.isEmpty()) {
      return 0;
    }
    String [] splittedString = numbers.split(",");
    if (splittedString.length < 2) {
      return Integer.valueOf(splittedString[0]);
    }
    return null;
  }

}
