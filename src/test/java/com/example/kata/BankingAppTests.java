package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BankingAppTests {


  private static Account account;

  @BeforeAll
  static void setup() {
    account = new Account(0);
  }

  @Test
  void call_deposit_check_balance() {
    account.deposit(500);
    assertEquals(500, account.getBalance());
    assertEquals(1, account.getOverviewStatements().size());

  }

  @Test
  void call__withdraw_check_balance() {
    account.withdraw(100);
    assertEquals(400, account.getBalance());
    assertEquals(2, account.getOverviewStatements().size());
  }

  @Test
  void call_printStatement_check_output() {
    account.printStatement();
    assertEquals(2, account.getOverviewStatements().size());

  }


}
