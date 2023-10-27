package com.example.kata;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BarcodeScannerTests {

  @Mock
  InputReader reader;
  @Mock
  OutputWriter writer;

  @Autowired
  BarcodeTable table;


  @ParameterizedTest
  @MethodSource("correctCases")
  void call_scanBarcode_with_valid_input(String[] input, String[] output) {
    var barcodeScanner = new BarcodeScanner(reader, writer, table);
    when(reader.read()).thenReturn(input[0],
        Arrays.stream(input).skip(1).toList().toArray(new String[0]));

    barcodeScanner.run();
    for (String price: output) {
      verify(writer).write(price);
    }

  }

  public static Stream<Arguments> correctCases() {
    return Stream.of(
        Arguments.of(new String[]{"12345", "stop"}, new String[]{"The price is $7.25"}),
        Arguments.of(new String[]{"23456", "stop"}, new String[]{"The price is $12.50"}),
        Arguments.of(new String[]{"12345", "23456", "stop"}, new String[]{"The price is $7.25",
            "The price is $12.50"}),
        Arguments.of(new String[]{"99999", "stop"}, new String[]{"barcode not found"}),
        Arguments.of(new String[]{"", "stop"}, new String[]{"empty barcode"}),
        Arguments.of(new String[]{"12345", "23456", "total", "stop"}, new String[]{"The price is $7.25",
            "The price is $12.50", "Total amount is $19.75"})
    );
  }
}
