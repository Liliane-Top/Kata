package com.example.kata.exceptions;

import java.util.List;
import lombok.Getter;

@Getter
public class InvalidInputForCalculatorException extends Exception {

  private final String message;

  public InvalidInputForCalculatorException(List<String> messages) {
    this.message = printErrorMessage(messages);
  }

  public String printErrorMessage(List<String> messages) {
    StringBuilder combinedMessage = new StringBuilder();

    for (String errorMessage : messages) {
      if (combinedMessage.isEmpty()) {
        combinedMessage.append(errorMessage);
      } else if (combinedMessage.toString().contains("Negative")) {
        combinedMessage.append(",").append(errorMessage.substring(31));
      } else if (messages.size() >1) {
        combinedMessage.insert(0,errorMessage + "\n");
      }
    }
    return combinedMessage.toString();
  }
}
