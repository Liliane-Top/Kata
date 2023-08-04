package com.example.kata;

import java.util.List;

public class Delimiters {

  public static List<String> changeDelimiter(String input) {
    String[] splittedString = input.split("\n");
    return List.of(splittedString[0].replace("//", ""));
  }


}
