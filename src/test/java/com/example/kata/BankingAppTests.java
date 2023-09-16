package com.example.kata;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class BankingAppTests {


  private Account account;
  @Mock
  private StatementWriter writer;
  @Captor
  private ArgumentCaptor<AccountStatement> statement;

  @BeforeEach
  void setup() {
    account = new Account(writer);
  }

  @Test
  void call_deposit_check_balance() {
    account.deposit(500);
    account.printStatement();
    verify(writer).write(statement.capture());

    AccountStatement result = statement.getValue();
    Assertions.assertEquals(500, result.balance());

  }

  @Test
  void call_withdraw_check_balance() {
    account.withdraw(100);
    account.printStatement();
    verify(writer, times(1)).write(statement.capture());

    AccountStatement result = statement.getValue();
    Assertions.assertEquals(-100, result.balance());

  }

  @Test
  void call_deposit_and_withdraw_check_balance() {
    account.deposit(1000);
    account.withdraw(100);
    account.deposit(500);
    account.printStatement();
    verify(writer, times(3)).write(statement.capture());

    AccountStatement result = statement.getValue();
    Assertions.assertEquals(1400, result.balance());

  }

}