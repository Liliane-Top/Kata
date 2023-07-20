package com.example.kata;

import static java.lang.Character.isUpperCase;

import com.example.kata.exceptions.InvalidPasswordException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Kata3 {
  private List<Integer> errorcode = new ArrayList<>();

  public boolean validatePassword(String password) throws InvalidPasswordException {

    if (!validateLength(password)) {
      errorcode.add(1);
    }

    if (!validateAtLeastTwoDigits(password)) {
      errorcode.add(2);
    }

    if (!validateContainsCapitalLetter(password)) {
      errorcode.add(3);
    }

    if (!validateContainsSpecialCharacter(password)) {
      errorcode.add(4);
    }

    if (errorcode.isEmpty()) {
      return true;
    } else {
      throw new InvalidPasswordException(errorcode);
    }
  }


  private boolean validateLength(String password) {
    return password.length() >= 8 ? true : false;
  }

  private boolean validateAtLeastTwoDigits(String password) {
    int count = 0;
    for (String element : password.split("")) {
      if (StringUtils.isNumeric(element)) {
        count++;
      }
    }
    return count >= 2 ? true : false;
  }

  private boolean validateContainsCapitalLetter(String password) {
    for (int i = 0; i < password.length(); i++) {
      if (Character.isUpperCase(password.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  private boolean validateContainsSpecialCharacter(String password) {
    for (int i = 0; i < password.length(); i++) {
      if (!Character.isDigit(password.charAt(i))
          && !Character.isLetter(password.charAt(i))
          && !Character.isWhitespace(password.charAt(i))) {
        return true;
      }
    }
    return false;
  }
}
