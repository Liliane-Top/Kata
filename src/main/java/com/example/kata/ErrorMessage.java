package com.example.kata;

public class ErrorMessage {

  public static StringBuilder errorMessages = new StringBuilder();

  public static void createErrorMessage(String errorMessage) {
    errorMessages.append(errorMessage);

  }

}
