package com.example.kata.validators;

public class ContainsUppercaseValidator implements PasswordValidator {

  @Override
  public void validate(String password) throws IllegalArgumentException {
    int count = 0;
    for (int i = 0; i < password.length(); i++) {
      if (Character.isUpperCase(password.charAt(i))) {
        count++;
      }
    }
    if(count == 0){
      throw new IllegalArgumentException("Password must contain at least one capital letter.");
    }
  }
}
