package com.example.kata;


import java.time.LocalDate;

public record AccountStatement(LocalDate date, Integer amount, Integer balance) {

  @Override
  public String toString() {
    return String.format("Date       | Amount | Balance%n%10s |%7d |%8d", date, amount, balance);
  }
}
