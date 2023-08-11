package com.example.kata.validators;

import com.example.kata.ErrormessageBuilder;

public class ContainsSpecialCharacterValidator implements PasswordValidator {

  @Override
  public void validate(String password, ErrormessageBuilder errors) {
    int count = 0;
    for (int i = 0; i < password.length(); i++) {
      if (!Character.isDigit(password.charAt(i))
          && !Character.isLetter(password.charAt(i))
          && !Character.isWhitespace(password.charAt(i))) {
        count++;
      }
    }
    if(count == 0){
      errors.addErrorMessage("Password must contain at least one special character.");
    }
  }
}
