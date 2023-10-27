package com.example.kata;

import java.util.Hashtable;
import org.springframework.stereotype.Component;

@Component
public class BarcodeTable {

  private final Hashtable<String, String> barcodePriceTable;

  public BarcodeTable() {
    barcodePriceTable = new Hashtable<>();
    barcodePriceTable.put("12345", "7.25");
    barcodePriceTable.put("23456", "12.50");

  }

  public String getPrice(String barcode) {
    return barcodePriceTable.get(barcode);
  }


}
