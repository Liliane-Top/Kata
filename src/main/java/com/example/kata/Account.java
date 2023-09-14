package com.example.kata;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Account {

  private Integer balance;
  private List<AccountStatement> overviewStatements;


  public Account(Integer balance) {
    this.balance = balance;
    overviewStatements = new ArrayList<>();
  }

  public void deposit(int amount) {
    balance += amount;
    overviewStatements.add(new AccountStatement(LocalDate.now(), amount, balance));

  }

  public void withdraw(int amount) {
    balance -= amount;
    overviewStatements.add(new AccountStatement(LocalDate.now(), amount, balance));
  }

  public void printStatement() {
    for (AccountStatement statement: overviewStatements) {
      System.out.println(statement);

    }
  }

}
