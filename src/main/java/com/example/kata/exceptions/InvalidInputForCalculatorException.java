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
    message = "";

    for (String errorMessage : messages) {
      if (message.contains("Negative") ) {
        message += "," + errorMessage.substring(31);
      } else if (errorMessage.contains("expected")) {
        message += errorMessage;
      } else if(message.isEmpty()) {
        message += errorMessage;
      } else {
        message = errorMessage + "\n" + message;
      }
    }
    return message;
  }
}
