package com.example.kata;

import java.util.ArrayList;
import java.util.List;

public class BarcodeScanner {
  private final InputReader inputReader;
  private final OutputWriter outputWriter;

  public BarcodeScanner(InputReader inputReader, OutputWriter outputWriter) {
    this.inputReader = inputReader;
    this.outputWriter = outputWriter;
  }

  private final List<String> prices = new ArrayList<>();

  public String run() {
    return getInputFromConsole();
  }

  private String getInputFromConsole() {
//    outputWriter.write("Please enter a barcode: ");
    String price = null;

    do {
      String input = inputReader.read();
      if (input.equals("stop")) {
        break;
      }
//      outputWriter.write("You entered the following: " + input);
      price = scanBarcode(input);
      outputWriter.write(price);
//      outputWriter.write("Please enter a barcode: ");
    }
    while (true);

    return price;
  }

  private String scanBarcode(String barcode) {

    return switch (barcode) {
      case "12345" -> {
        prices.add("7.25");
        yield "The price is $7.25";
      }
      case "23456" -> {
        prices.add("12.50");
        yield "The price is $12.50";
      }
      case "99999" -> "barcode not found";
      case "" -> "empty barcode";
      case "total" -> getTotal();
      default -> "unknown";
    };
  }

  private String getTotal() {
    double total = 0.0;
    for (String price : prices) {
      total += Double.parseDouble(price);
    }
    return "Total amount is $" + total;
  }

  public interface InputReader {
    String read();
  }
  public interface OutputWriter {
    void write(String output);
  }
}
