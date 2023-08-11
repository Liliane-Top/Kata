package com.example.kata;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Tokenizer {

  public record Tokenized(String input, List<String> delimiters) {
  }

  public Tokenized tokenize(String input, ErrorBuilder errors) {
    List<String> delimiters = List.of(",", "\n");
    if (validate(input, errors)) {
      String[] splittedString = input.split("\n");
      delimiters = List.of(splittedString[0].substring(2));
      input = splittedString[1];
    }
    return new Tokenized(input, delimiters);
  }
  private boolean validate(String input, ErrorBuilder errors){
    String normalized = validateInputEnding(input, errors);
    return validateChangedDelimiter(normalized);
  }
  private String validateInputEnding(String input, ErrorBuilder errors) {
    return input.matches("(.*\\n*)*\\D") ? removeDelimiterAtEnd(input, errors) : input;
  }

  private String removeDelimiterAtEnd(String input, ErrorBuilder errors) {
  errors.addErrorMessage("Separator at the end is not allowed.");
    return input.substring(0, input.length() - 1);
  }


  private boolean validateChangedDelimiter(String input) {
    return input.startsWith("//");
  }

}

