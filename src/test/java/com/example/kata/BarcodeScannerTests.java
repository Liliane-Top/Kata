package com.example.kata;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BarcodeScannerTests {

//  private static BarcodeScanner scanner;
//
//  @BeforeAll
//  static void setUp() {
//    scanner = new BarcodeScanner();
//  }

  @ParameterizedTest
  @MethodSource("correctCases")
  void call_scanBarcode_with_valid_input(String[] input, String output) {
    Assertions.assertEquals(output, BarcodeScanner.getPrice(input));

  }

  public static Stream<Arguments> correctCases() {
    return Stream.of(
        Arguments.of(new String[]{"12345"}, "$7.25"),
        Arguments.of(new String[]{"23456"}, "$12.50"),
        Arguments.of(new String[]{"99999"}, "barcode not found"),
        Arguments.of(new String[]{""}, "empty barcode"),
        Arguments.of(new String[]{"12345", "23456", "total"}, "$19.75")
    );
  }
}
