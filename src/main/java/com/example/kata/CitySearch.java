package com.example.kata;

public class CitySearch {

  public String search(String searchText) {
    String citiesFound = "";

    if (searchText.length() < 2) {
      return citiesFound;
    }

    for (String city : ListOfCities.cities) {
      if (city.toLowerCase().contains(searchText.toLowerCase())) {
        citiesFound += String.format("%s ", city);
      }
    }
    return citiesFound.trim();
  }

}
