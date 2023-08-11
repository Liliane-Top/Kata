package com.example.kata;

import com.example.kata.validators.AtLeast2DigitsValidator;
import com.example.kata.validators.ContainsSpecialCharacterValidator;
import com.example.kata.validators.ContainsUppercaseValidator;
import com.example.kata.validators.LengthValidator;

public record PasswordValidation(LengthValidator lengthValidator,
                                 AtLeast2DigitsValidator digitsValidator,
                                 ContainsUppercaseValidator uppercaseValidator,
                                 ContainsSpecialCharacterValidator specialCharacterValidator) {

  public boolean validatePassword(String password) {

    ErrormessageBuilder errors = new ErrormessageBuilder();

    lengthValidator.validate(password, errors);

    digitsValidator.validate(password, errors);

    uppercaseValidator.validate(password, errors);

    specialCharacterValidator.validate(password, errors);

    if (errors.hasNoErrors()) {
      return true;
    } else {
      throw new IllegalArgumentException(errors.getException().getMessage().trim());
    }
  }

}
