package com.example.kata;

public class CitySearch {

  public String search(String searchText) {
    StringBuilder citiesFound = new StringBuilder();

    if (searchText.equals("*")) {
      for (String city : ListOfCities.cities) {
        citiesFound.append(String.format("%s ", city));
      }
      return citiesFound.toString().trim();
    }

    if (searchText.length() < 2) {
      return citiesFound.toString();
    }

    for (String city : ListOfCities.cities) {
      if (city.toLowerCase().contains(searchText.toLowerCase())) {
        citiesFound.append(String.format("%s ", city));
      }
    }
    return citiesFound.toString().trim();
  }

}
