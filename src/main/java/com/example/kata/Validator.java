package com.example.kata;

public class Validator {

  public static String validateInputEnding(String input) {
    String validInput = input.matches(".*\\D") ? removeDelimiterAtEnd(input) : input;
    return validInput;
  }

  private static String removeDelimiterAtEnd(String input) {
    ErrorMessage.createErrorMessage("Separator at the end is not allowed");
    return input.substring(0, input.length() - 1);
  }


  public static boolean validateChangedDelimiter(String input) {
    return input.startsWith("//");
  }
}