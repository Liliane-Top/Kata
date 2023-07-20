package com.example.kata.exceptions;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AggregateException extends Exception {

  private final List<Exception> innerExceptions = new ArrayList<>();

  public void addException(Exception ex) {
    innerExceptions.add(ex);
  }
}
