package com.example.kata.validators;

import org.apache.commons.lang3.StringUtils;

public class AtLeast2DigitsValidator implements PasswordValidator {

  @Override
  public void validate(String password) throws IllegalArgumentException {
    int count = 0;
    for (String element : password.split("")) {
      if (StringUtils.isNumeric(element)) {
        count++;
      }
    }
    if (count < 2) {
      throw new IllegalArgumentException("Password must contain at least 2 numbers.");
    }
  }
}
