package com.webischia.libraryfrontend.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class City implements Serializable {
    int cityID;
    int countryID;
    String cityName;

    public City(int cityID, int countryID, String cityName) {
        this.cityID = cityID;
        this.countryID = countryID;
        this.cityName = cityName;
    }
}
