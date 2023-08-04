package com.example.kata;

public class ErrorMessage {

  public static StringBuilder errorMessages = new StringBuilder();

  public static String createErrorMessage(String errorMessage) {
    errorMessages.append(errorMessage);
    return errorMessages.toString();

  }

}
