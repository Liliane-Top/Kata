package com.example.kata;

import lombok.Builder;

@Builder
public record Kata1(Integer number, String numberAsString) {

public String convertIntegerToString(Integer number){
  return number.toString();
}

}
