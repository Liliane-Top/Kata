package com.example.kata;

import static com.example.kata.ListOfCities.cities;

import java.util.stream.Collectors;

public class CitySearch {

  public String search(String searchText) {

    if (searchText.equals("*")) {
      return String.join(" ", cities);
    }

    if (searchText.length() < 2) {
      return "";
    }

    return cities.stream()
        .filter(city -> city.toLowerCase().contains(searchText.toLowerCase()))
        .collect(Collectors.joining(" "));
  }

}
