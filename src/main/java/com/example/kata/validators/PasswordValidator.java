package com.example.kata.validators;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface PasswordValidator {

  void validate(String password) throws IllegalArgumentException;

  static PasswordValidator combine(PasswordValidator... validators){
    return password -> {
      List<String> errors = Arrays.stream(validators)
              .map(validator -> {
                try {
                  validator.validate(password);
                } catch (IllegalArgumentException e) {
                  return e.getMessage();
                }
                return null;
              })
              .filter(Objects::nonNull)
              .toList();

      if (!errors.isEmpty()) {
        throw new IllegalArgumentException(String.join("\n", errors));
      }
    };
  }

}
