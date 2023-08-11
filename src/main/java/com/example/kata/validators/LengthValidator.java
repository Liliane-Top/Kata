package com.example.kata.validators;

import com.example.kata.ErrormessageBuilder;

public class LengthValidator implements PasswordValidator {

  @Override
  public void validate(String password, ErrormessageBuilder errors) {
    if(password.length() < 8) {
      errors.addErrorMessage("Password must be at least 8 characters.");
    }
  }
}
