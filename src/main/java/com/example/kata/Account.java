package com.example.kata;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Account {

  private Integer balance;

  private StatementWriter writer;
  private List<AccountStatement> overviewStatements;


  public Account(StatementWriter writer) {
    this.balance = 0;
    this.writer = writer;
    this.overviewStatements = new ArrayList<>();
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
    for (AccountStatement statement : overviewStatements) {
      writer.write(statement);
      System.out.println(statement);
    }

  }

}
