package com.example.kata;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BarcodeScannerTests {

  private static BarcodeScanner scanner;

  @BeforeAll
  static void setUp() {
    scanner = new BarcodeScanner();
  }

  @ParameterizedTest
  @MethodSource("correctCases")
  void call_scanBarcode_with_valid_input(String input, String output){
    Assertions.assertEquals(output, scanner.scanBarcode(input));

  }
  public static Stream<Arguments>  correctCases(){
    return Stream.of(
        Arguments.of( "12345", "$7.25"),
        Arguments.of("23456", "$12.50"),
        Arguments.of("99999", "barcode not found")
    );
  }


  @ParameterizedTest
  @MethodSource("incorrectCases")
  void call_displayBarcode_with_invalid_input(){


  }

  public static Stream<Arguments>  incorrectCases(){
    return Stream.of(
        Arguments.of("barcode not found", "99999")
    );
  }





}
