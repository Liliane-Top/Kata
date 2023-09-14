package com.example.kata;

import lombok.Getter;

@Getter
public class Account {

  private Integer balance;


  public Account(Integer balance) {
    this.balance = balance;
  }

  public void deposit(int amount) {
    balance += amount;
  }

  public void withdraw(int amount) {

  }

  public void printStatement() {

  }

}
