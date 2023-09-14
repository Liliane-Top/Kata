package com.example.kata;


import java.time.LocalDate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountStatement {

  private final LocalDate date;
  private final Integer amount;
  private final Integer balance;



  @Override
  public String toString() {
    return "AccountStatement{" +
        "date=" + date +
        ", amount=" + amount +
        ", balance=" + balance +
        '}';
  }
}
