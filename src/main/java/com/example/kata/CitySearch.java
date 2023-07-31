package com.example.kata;

public class CitySearch {

  public String search(String searchKey) {
    for(String city : ListOfCities.cities) {
      if(searchKey.equals(city)){
        return city;
      }
    }
    return null;
  }

}
