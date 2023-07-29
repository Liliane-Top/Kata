package com.example.kata.exceptions;

import java.util.List;
import lombok.Getter;

@Getter
public class InvalidInputForCalculatorException extends Exception {

  private String message;

  public InvalidInputForCalculatorException(List<String> messages) {
    this.message = printErrorMessage(messages);
  }

  public String printErrorMessage(List<String> messages) {
    StringBuilder message = new StringBuilder();

    for (String errorMessage : messages) {
      if (message.isEmpty()) {
        message.append(errorMessage);
      } else if (message.toString().contains("Negative")) {
        message.append("," + errorMessage.substring(31));
      } else if (messages.size() >1) {
        message.insert(0,errorMessage + "\n");
      }
    }
    return message.toString();
  }
}
