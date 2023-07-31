package com.example.kata;

public class CitySearch {

  public String search(String searchText) {
    StringBuilder citiesFound = new StringBuilder();

    for (String city : ListOfCities.cities) {
      if (searchText.equals("*")) {
        citiesFound.append(String.format("%s ", city));
      }   else if (searchText.length() < 2) {
        return "";
      } else if (city.toLowerCase().contains(searchText.toLowerCase())) {
        citiesFound.append(String.format("%s ", city));
      }
    }
    return citiesFound.toString().trim();
  }

}
