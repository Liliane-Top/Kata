package com.example.kata;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BankingAppTests {

  Account account = new Account(0);

  @ParameterizedTest
  @MethodSource("happyFlow")
  void bankingApp_happyFlow(Integer amount, Integer balance) {
    account.deposit(amount);

    Assertions.assertTrue(account.getBalance().equals(balance));


  }

  static Stream<Arguments> happyFlow() {
    return Stream.of(
        Arguments.of(500, 500)
    );
  }


}
