package com.example.kata;

public class ErrormessageBuilder {
  private final StringBuilder errorMessages = new StringBuilder();

  public  void addErrorMessage(String errorMessage) {
    errorMessages.append(errorMessage);
    errorMessages.append("\n");
  }

  public boolean hasNoErrors(){
    return errorMessages.isEmpty();
  }

  public IllegalArgumentException getException() {
    return new IllegalArgumentException(errorMessages.toString().trim());
  }

}
