package com.example.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BarcodeScanner {
  public static List<String> prices = new ArrayList<>();


  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    System.out.println("Please enter a barcode: ");


    do{
      String input = in.nextLine();
      if(input.equals("stop")) break;
      System.out.println("You entered the following: " + input);

      System.out.println(scanBarcode(input) + "\n");
      System.out.println("Please enter a barcode: ");
    }
    while (in.hasNextLine());

    in.close();
  }

  private static String getTotal(List<String> prices) {
    double total = 0.0;
    for(String price: prices){
      total += Double.parseDouble(price);
    }
    return "Total amount is $" + total;
  }

  public static String scanBarcode(String barcode){

    return switch (barcode) {
      case "12345" -> {
         prices.add("7.25");
         yield "The price is $7.25";
      }
      case "23456" -> {
        prices.add("12.50");
        yield  "The price is $12.50";
      }
      case "99999" -> "barcode not found";
      case "" -> "empty barcode";
      case "total" -> getTotal(prices);
      default -> "unknown";
    };


  }


  }

