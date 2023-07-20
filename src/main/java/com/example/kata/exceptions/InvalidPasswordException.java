package com.example.kata.exceptions;

import java.util.List;
import lombok.Getter;

@Getter
public class InvalidPasswordException extends Exception {

  private String message;
  private List<Integer> errorCodes;
  public InvalidPasswordException(List<Integer> errorCodes) {
    this.errorCodes = errorCodes;
    this.message = printMessage();
  }

  public String printMessage() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Integer violatedCondition : errorCodes) {

      if (violatedCondition == 1) {
        stringBuilder.append("Password must be at least 8 characters\n");
      }
      if (violatedCondition == 2) {
        stringBuilder.append("Password must contain at least 2 numbers\n");
      }
      if(violatedCondition == 3) {
        stringBuilder.append("Password must contain at least one capital letter\n");
      }
      if(violatedCondition == 4) {
        stringBuilder.append("Password must contain at least one special character\n");
      }
    }
    return stringBuilder.toString();
  }
}
