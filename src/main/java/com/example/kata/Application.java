package com.example.kata;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var barcodeScanner = new BarcodeScanner(scanner::nextLine, System.out::println);
        barcodeScanner.run();
    }
}
