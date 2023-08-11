package com.example.kata.validators;

import com.example.kata.ErrormessageBuilder;
import org.apache.commons.lang3.StringUtils;

public class AtLeast2DigitsValidator implements PasswordValidator {

  @Override
  public void validate(String password, ErrormessageBuilder errors) {
    int count = 0;
    for (String element : password.split("")) {
      if (StringUtils.isNumeric(element)) {
        count++;
      }
    }
    if (count < 2) {
      errors.addErrorMessage("Password must contain at least 2 numbers.");
    }
  }
}
