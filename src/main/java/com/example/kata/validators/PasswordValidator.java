package com.example.kata.validators;

import com.example.kata.ErrormessageBuilder;

public interface PasswordValidator {

  void validate(String password, ErrormessageBuilder message);

}
