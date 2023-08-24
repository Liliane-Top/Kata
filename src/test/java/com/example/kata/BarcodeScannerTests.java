package com.example.kata;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BarcodeScannerTests {

//  private static BarcodeScanner scanner;
//
//  @BeforeAll
//  static void setUp() {
//    scanner = new BarcodeScanner();
//  }
  @Mock
  BarcodeScanner.InputReader reader;
  @Mock
  BarcodeScanner.OutputWriter writer;

  @ParameterizedTest
  @MethodSource("correctCases")
  void call_scanBarcode_with_valid_input(String[] input, String output) {
    var barcodeScanner = new BarcodeScanner(reader, writer);
    when(reader.read()).thenReturn(input[0], Arrays.stream(input).skip(1).toList().toArray(new String[0]));
    Assertions.assertEquals(output, barcodeScanner.run());
    verify(writer).write(output);
  }

  public static Stream<Arguments> correctCases() {
    return Stream.of(
        Arguments.of(new String[]{"12345", "stop"}, "The price is $7.25"),
        Arguments.of(new String[]{"23456", "stop"}, "The price is $12.50"),
        Arguments.of(new String[]{"99999", "stop"}, "barcode not found"),
        Arguments.of(new String[]{"", "stop"}, "empty barcode"),
        Arguments.of(new String[]{"12345", "23456", "total", "stop"}, "Total amount is $19.75")
    );
  }
}
