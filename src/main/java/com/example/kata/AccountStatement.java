package com.example.kata;


import java.time.LocalDate;

public record AccountStatement(LocalDate date, Integer amount, Integer balance) {


  //FIXME: check how to format with proper spacing
  @Override
  public String toString() {
    return String.format(" Date       | Amount | Balance\n %s | %d    | %d", date, amount, balance);
  }
}
