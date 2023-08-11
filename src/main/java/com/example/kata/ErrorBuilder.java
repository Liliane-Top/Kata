package com.example.kata;

public class ErrorBuilder {

  private StringBuilder errorMessages = new StringBuilder();

  public void addErrorMessage(String errorMessage) {
    errorMessages.append(errorMessage);
    errorMessages.append("\n");
  }

  public boolean hasError() {
    return !errorMessages.isEmpty();
  }

  public NumberFormatException getException() {
    return new NumberFormatException(errorMessages.toString().trim());
  }
}
