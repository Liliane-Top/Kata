package com.example.kata.validators;

import com.example.kata.ErrormessageBuilder;

public class ContainsUppercaseValidator implements PasswordValidator {

  @Override
  public void validate(String password, ErrormessageBuilder errors) {
    int count = 0;
    for (int i = 0; i < password.length(); i++) {
      if (Character.isUpperCase(password.charAt(i))) {
        count++;
      }
    }
    if(count == 0){
      errors.addErrorMessage("Password must contain at least one capital letter.");
    }
  }
}
