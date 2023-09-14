package com.example.kata;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BankingAppTests {


  private static Account account;

  @BeforeAll
  static void setup(){
    account = new Account(0);
  }

  @Test
  void call_deposit_check_balance(){
    account.deposit(500);
    assertEquals(500, account.getBalance());
  }

  @Test
  void call__withdraw_check_balance(){
    account.withdraw(100);
    assertEquals(400, account.getBalance());
  }


}
