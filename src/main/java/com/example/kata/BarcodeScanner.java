package com.example.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BarcodeScanner {

  private final InputReader reader;
  private final OutputWriter writer;
  private final BarcodeTable table;

  private static List<String> prices = new ArrayList<>();

  public void run() {
    do {
      String input = reader.read();
      if (input.equals("stop")) {
        break;
      }
      writer.write(scanBarcode(input));
    } while (true);
  }


  private String scanBarcode(String barcode) {

    if (barcode.isEmpty()) {
      return "empty barcode";
    }

    if (barcode.equals("total")) {
      return getTotal();
    }

    return Optional.ofNullable(table.getPrice(barcode))
        .map(price -> {
          prices.add(price);
         return "The price is $" + price;
        })
        .orElse("barcode not found");

  }


  private static String getTotal() {
    double total = 0.0;
    for (String price : prices) {
      total += Double.parseDouble(price);
    }
    return "Total amount is $" + total;
  }
}
