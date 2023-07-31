package com.example.kata;

import java.util.stream.Collectors;

public class CitySearch {

  public String search(String searchText) {

    if (searchText.equals("*")) {
      return ListOfCities.cities.stream().collect(Collectors.joining(" "));
    }

    if (searchText.length() < 2) {
      return "";
    }

    return ListOfCities.cities.stream()
        .filter(city -> city.toLowerCase().contains(searchText.toLowerCase()))
        .collect(Collectors.joining(" "));
  }

}
