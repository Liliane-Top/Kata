package com.example.kata.validators;

public class LengthValidator implements PasswordValidator {

  @Override
  public void validate(String password) throws IllegalArgumentException {
    if(password.length() < 8) {
      throw new IllegalArgumentException("Password must be at least 8 characters.");
    }
  }
}
