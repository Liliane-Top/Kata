package com.example.kata;

import java.util.Scanner;

public class BarcodeScanner {
  //  Scanner in = new Scanner(System.in);
  //
  //        String s = in.nextLine();
  //        System.out.println("You entered string " + s);
  //
  //        int a = in.nextInt();
  //        System.out.println("You entered integer " + a);
  //
  //        float b = in.nextFloat();
  //        System.out.println("You entered float " + b);

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Please enter a barcode: ");

    String barcode = input.nextLine();
    System.out.println("You entered the following barcode " + barcode);

    System.out.println("The price is " + scanBarcode(barcode));



  }

  public static String scanBarcode(String barcode){

    return switch (barcode) {
      case "12345" -> "$7.25";
      case "23456" -> "$12.50";
      case "99999" -> "barcode not found";
      case "" -> "empty barcode";
      default -> "unknown";
    };


  }


  }

